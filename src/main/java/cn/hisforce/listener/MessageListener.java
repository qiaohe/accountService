package cn.hisforce.listener;

import cn.hisforce.domain.settlement.SettlementCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Johnson on 2016/7/17.
 */
public class MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);


    private CountDownLatch latch;
    private SettlementCenter settlementCenter;

    @Autowired
    public MessageListener(CountDownLatch latch, SettlementCenter settlementCenter) {
        this.latch = latch;
        this.settlementCenter = settlementCenter;
    }

    public void onMessage(String message) {
        settlementCenter.settle(message);
        latch.countDown();
    }
}
