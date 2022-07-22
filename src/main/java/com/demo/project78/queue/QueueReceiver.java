package com.demo.project78.queue;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueReceiver {

    @RabbitListener(queues = AmqpConfig.SIMPLE_QUEUE)
    public void receive(Customer customer) {
        log.info("{} Received '{}'", AmqpConfig.SIMPLE_QUEUE, customer);

        //Simulate a failure on processing
        if (customer.getName().equals("NO_NAME")) {
            throw new RuntimeException("No customer name!");
        }
    }
}
