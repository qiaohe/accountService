package cn.hisforce.service;

import cn.hisforce.domain.TransactionFlow;

/**
 * Created by Johnson on 2016/7/18.
 */
public interface AccountService {
    public TransactionFlow deposit(Long hospitalId, Double amount);
}
