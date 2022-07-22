package com.demo.project78.queue;

import com.demo.project78.config.AmqpConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue simpleQueue() {
        return new Queue(AmqpConfig.SIMPLE_QUEUE, true);
    }

}




