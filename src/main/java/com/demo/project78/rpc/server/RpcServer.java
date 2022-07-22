package com.demo.project78.rpc.server;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RpcServer {

    @SneakyThrows
    @RabbitListener(queues = AmqpConfig.RPC_QUEUE)
    public String receive(Customer customer) {
        log.info("{} Received '{}'", AmqpConfig.RPC_QUEUE, customer);
        TimeUnit.SECONDS.sleep(5);
        return "Hello world, " + customer.getName();
    }


}
