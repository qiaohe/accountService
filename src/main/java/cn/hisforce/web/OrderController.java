package cn.hisforce.web;

import cn.hisforce.domain.Account;
import cn.hisforce.domain.MedicalOrder;
import cn.hisforce.repository.MedicalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Johnson on 2016/7/17.
 */
@RestController
public class OrderController {
    @Autowired
    private MedicalOrderRepository medicalOrderRepository;

    @RequestMapping("/orders/{id}")
    public MedicalOrder getOrder(@PathVariable("id") Long orderId) {
        return medicalOrderRepository.findOne(orderId);
    }

}
