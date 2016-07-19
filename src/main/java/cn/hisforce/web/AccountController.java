package cn.hisforce.web;

import cn.hisforce.domain.Account;
import cn.hisforce.domain.TransactionFlow;
import cn.hisforce.repository.AccountRepository;
import cn.hisforce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Johnson on 2016/7/17.
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts/deposit", method = RequestMethod.POST)
    @ResponseBody
    public TransactionFlow deposit(@RequestBody Map map) {
        Long hospitalId = Long.valueOf(map.get("hospitalId").toString());
        Double amount = Double.valueOf(map.get("amount").toString());
        return accountService.deposit(hospitalId, amount);
    }
}
