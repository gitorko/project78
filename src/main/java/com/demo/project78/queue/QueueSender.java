package com.demo.project78.queue;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class QueueSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(Customer customer) {
        rabbitTemplate.convertAndSend(AmqpConfig.SIMPLE_QUEUE, customer);
        log.info("Sent to Simple Queue : {}", customer);
    }
}
