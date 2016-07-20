package cn.hisforce.service;

import cn.hisforce.domain.TransactionFlow;
import cn.hisforce.domain.transaction.TransactionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Johnson on 2016/7/19.
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private TransactionHandler transactionHandler;

    @Override
    public TransactionFlow deposit(Long hospitalId, Double amount) {
        return transactionHandler.perform(hospitalId, 1, 206, amount);
    }

    @Override
    public TransactionFlow withdraw(Long uid, Double amount) {
        return transactionHandler.perform(uid, 0, 106, -1 * amount);
    }
}
