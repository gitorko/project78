package com.demo.project78.exchange;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExchangeReceiver {

    @RabbitListener(queues = AmqpConfig.DIRECT_QUEUE)
    public void receive(Customer customer) {
        log.info("{} Received '{}'", AmqpConfig.DIRECT_QUEUE, customer);
    }

}
