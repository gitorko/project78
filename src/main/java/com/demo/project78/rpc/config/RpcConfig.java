package com.demo.project78.rpc.config;

import com.demo.project78.config.AmqpConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcConfig {

    @Bean
    public Queue rpcQueue() {
        return new Queue(AmqpConfig.RPC_QUEUE, true);
    }

    @Bean
    TopicExchange rpcExchange() {
        return new TopicExchange(AmqpConfig.RPC_EXCHANGE);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(rpcQueue()).to(rpcExchange()).with(AmqpConfig.RPC_KEY);
    }

}

