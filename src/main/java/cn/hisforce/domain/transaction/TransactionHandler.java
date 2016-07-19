package cn.hisforce.domain.transaction;

import cn.hisforce.domain.Account;
import cn.hisforce.domain.AngelGuiderShare;
import cn.hisforce.domain.TransactionFlow;
import cn.hisforce.repository.AccountRepository;
import cn.hisforce.repository.RedisRepository;
import cn.hisforce.repository.TransactionCodeRepository;
import cn.hisforce.repository.TransactionFlowRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Johnson on 2016/7/17.
 */
@Component(value = "transactionHander")
public class TransactionHandler {
    private static final String PADDING_FLOW_NO = "0";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionCodeRepository transactionCodeRepository;
    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private TransactionFlowRepository transactionFlowRepository;

    private Map<Integer, TransactionCode> transactionCodeMap = new HashMap<>();

    @PostConstruct
    private void init() {
        for (TransactionCode code : transactionCodeRepository.findAll()) {
            transactionCodeMap.put(code.getCode(), code);
        }
    }

    private Account getHospitalAccount(AngelGuiderShare share) {
        return accountRepository.findByUidAndType(share.getHospitalId(), 1);
    }

    private Account getAngelGuiderAccount(AngelGuiderShare share) {
        return accountRepository.findByUidAndType(share.getAngelGuider(), 0);
    }

    private Account getAgentAccount(AngelGuiderShare share) {
        return accountRepository.findByUidAndType(share.getAgency(), 0);
    }

    private String getTransactionFlowNo() {
        String flowNo = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        Long sequenceNo = redisRepository.getNext(Keys.transactionFlowKey());
        return String.format("%s-%s", flowNo, StringUtils.leftPad(String.valueOf(sequenceNo), 6, PADDING_FLOW_NO));
    }

    public TransactionFlow perform(Long uid, Integer type, Integer code, Double amount) {
        if (amount < 0.001) return null;
        Account account = accountRepository.findByUidAndType(uid, type);
        account.setBalance(account.getBalance() + amount);
        account.setAvailableBalance(account.getAvailableBalance() + amount);
        account.setUpdateDate(new Date());
        accountRepository.save(account);
        TransactionFlow flow = new TransactionFlow(account, transactionCodeMap.get(code),
                null, amount, getTransactionFlowNo());
        return transactionFlowRepository.save(flow);
    }

    public void handle(AngelGuiderShare share) {
        perform(share.getHospitalId(), 1, share.getOutTransactionCode(), share.getHospitalPayableAmount() * (-1));
        perform(999L, 1, share.getInTransactionCode(), share.getPlatformShareAmount());
        perform(share.getAngelGuider(), 0, 202, share.getAngelGuiderShareAmount());
        perform(share.getAgency() != null ? share.getAgency() : share.getAngelGuider(), 0, 201, share.getAgentShareAmount());
    }

}
