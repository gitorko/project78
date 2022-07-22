package com.demo.project78.exchange;

import com.demo.project78.config.AmqpConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {

    @Bean
    public Queue directQueue() {
        return new Queue(AmqpConfig.DIRECT_QUEUE, true);
    }

    @Bean
    TopicExchange directExchange() {
        return new TopicExchange(AmqpConfig.DIRECT_EXCHANGE);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(AmqpConfig.DIRECT_KEY);
    }

}
