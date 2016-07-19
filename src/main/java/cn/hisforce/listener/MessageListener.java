package cn.hisforce.listener;

import cn.hisforce.domain.AngelGuiderShare;
import cn.hisforce.domain.settlement.SettlementCenter;
import cn.hisforce.domain.transaction.TransactionHandler;
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
    private TransactionHandler transactionHandler;

    @Autowired
    public MessageListener(CountDownLatch latch, SettlementCenter settlementCenter, TransactionHandler transactionHandler) {
        this.latch = latch;
        this.settlementCenter = settlementCenter;
        this.transactionHandler = transactionHandler;
    }

    public void onMessage(String message) {
        AngelGuiderShare share = settlementCenter.settle(message);
        transactionHandler.handle(share);
        latch.countDown();
    }
}
