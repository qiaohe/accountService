package cn.hisforce.domain.transaction;

import cn.hisforce.domain.Account;
import cn.hisforce.domain.AngelGuiderShare;
import cn.hisforce.domain.Registration;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Johnson on 2016/7/17.
 */
@Component(value = "TransactionHander")
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

    private Account platformAccount;
    private Map<Integer, TransactionCode> transactionCodeMap = new HashMap<>();

    @PostConstruct
    private void init() {
        platformAccount = accountRepository.findByUidAndType(999L, 2);
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

    public void handle(AngelGuiderShare share) {
        Account account = getHospitalAccount(share);
        if (share.getHospitalPayableAmount() > 0) {
            TransactionFlow flow = new TransactionFlow(account, transactionCodeMap.get(share.getOutTransactionCode()), share, share.getHospitalPayableAmount(), getTransactionFlowNo());
            transactionFlowRepository.save(flow);
        }
        if (share.getPlatformShareAmount() > 0) {
            TransactionFlow platformFlow = new TransactionFlow(platformAccount, transactionCodeMap.get(share.getInTransactionCode()), share, share.getPlatformShareAmount(), getTransactionFlowNo());
            transactionFlowRepository.save(platformFlow);
        }
        if (share.getAngelGuiderShareAmount() > 0) {
            Account angelGuiderAccount = getAngelGuiderAccount(share);
            TransactionFlow angelGuideFlow = new TransactionFlow(angelGuiderAccount, transactionCodeMap.get(share.getInTransactionCode()), share, share.getAngelGuiderShareAmount(), getTransactionFlowNo());
            transactionFlowRepository.save(angelGuideFlow);
            if (share.getAgency() != null) {
                Account agentAccount = getAgentAccount(share);
                TransactionFlow agentFlow = new TransactionFlow(agentAccount, transactionCodeMap.get(share.getInTransactionCode()), share, share.getAgentShareAmount(), getTransactionFlowNo());
                transactionFlowRepository.save(agentFlow);
            } else {
                TransactionFlow agentFlow = new TransactionFlow(angelGuiderAccount, transactionCodeMap.get(share.getInTransactionCode()), share, share.getAgentShareAmount(), getTransactionFlowNo());
                transactionFlowRepository.save(agentFlow);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
