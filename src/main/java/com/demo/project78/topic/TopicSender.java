package com.demo.project78.topic;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TopicSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(Customer customer, String routingKey) {
        rabbitTemplate.convertAndSend(AmqpConfig.TOPIC_EXCHANGE, routingKey, customer);
        log.info("Topic with key: {} Sent to Exchange: {}", routingKey, customer);
    }
}
