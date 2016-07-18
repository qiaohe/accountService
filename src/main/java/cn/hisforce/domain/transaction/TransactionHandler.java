package cn.hisforce.domain.transaction;

import cn.hisforce.domain.Account;
import cn.hisforce.domain.AngelGuiderShare;
import cn.hisforce.domain.TransactionFlow;
import cn.hisforce.repository.AccountRepository;
import cn.hisforce.repository.RedisRepository;
import cn.hisforce.repository.TransactionCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Johnson on 2016/7/17.
 */
@Component(value = "TransactionHander")
public class TransactionHandler {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionCodeRepository transactionCodeRepository;
    @Autowired
    private RedisRepository redisRepository;

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

    public void handle(AngelGuiderShare share) {
//        Account account = getHospitalAccount(share);
//        TransactionFlow flow = new TransactionFlow(account,null, share, share.getAmount(), null);
//        redisRepository.getNext(new LocalTime())
//        share.createTransactionFlows();

    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }
}
