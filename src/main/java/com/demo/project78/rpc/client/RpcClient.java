package com.demo.project78.rpc.client;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RpcClient {

    private final RabbitTemplate rabbitTemplate;

    public void send(Customer customer, String routingKey) {
        rabbitTemplate.setReplyTimeout(60000);
        log.info("RPC Call with key: {} Sent to Exchange: {}", routingKey, customer);
        String response = (String) rabbitTemplate.convertSendAndReceive(AmqpConfig.RPC_EXCHANGE, routingKey, customer);
        log.info("RPC Call got '{}'", response);
    }

}
