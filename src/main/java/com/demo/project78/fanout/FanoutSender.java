package com.demo.project78.fanout;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FanoutSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(Customer customer, String routingKey) {
        rabbitTemplate.convertAndSend(AmqpConfig.FANOUT_EXCHANGE, routingKey, customer);
        //routing key doesnt matter
        log.info("Fanout with key: {} Sent to Exchange: {}", routingKey, customer);
    }
}
