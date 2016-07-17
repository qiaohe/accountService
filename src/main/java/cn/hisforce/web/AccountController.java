package cn.hisforce.web;

import cn.hisforce.domain.Account;
import cn.hisforce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2016/7/17.
 */
@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        return new Account();
    }

    @RequestMapping("/accounts")
    public List<Account> getAccounts() {
       return accountRepository.findAll();
    }
}
