package com.demo.project78.topic;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicReceiver {

    @RabbitListener(queues = AmqpConfig.TOPIC_QUEUE_1)
    public void receive1(Customer customer) {
        log.info("{} Received '{}'", AmqpConfig.TOPIC_QUEUE_1, customer);
    }

    @RabbitListener(queues = AmqpConfig.TOPIC_QUEUE_2)
    public void receive2(Customer customer) {
        log.info("{} Received '{}'", AmqpConfig.TOPIC_QUEUE_2, customer);
    }
}
