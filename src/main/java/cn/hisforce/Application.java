package cn.hisforce;

import cn.hisforce.domain.settlement.SettlementCenter;
import cn.hisforce.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Johnson on 2016/7/17.
 */
@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("settlement.queue"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageListener listener) {
        return new MessageListenerAdapter(listener, "onMessage");
    }

    @Bean
    MessageListener messageListener(CountDownLatch latch, SettlementCenter settlementCenter) {
        return new MessageListener(latch, settlementCenter);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    SettlementCenter settlementCenter() {
        return new SettlementCenter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
