package com.demo.project78.exchange;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangeSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(Customer customer, String routingKey) {
        rabbitTemplate.convertAndSend(AmqpConfig.DIRECT_EXCHANGE, routingKey, customer);
        log.info("Direct with key: {} Sent to Exchange: {}", routingKey, customer);
    }
}
