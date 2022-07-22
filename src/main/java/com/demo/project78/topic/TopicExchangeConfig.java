package com.demo.project78.topic;

import com.demo.project78.config.AmqpConfig;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    @Bean
    public Declarables topicBindings() {
        Queue topicQueue1 = new Queue(AmqpConfig.TOPIC_QUEUE_1, false);
        Queue topicQueue2 = new Queue(AmqpConfig.TOPIC_QUEUE_2, false);

        TopicExchange topicExchange = new TopicExchange(AmqpConfig.TOPIC_EXCHANGE);

        return new Declarables(
                topicQueue1,
                topicQueue2,
                topicExchange,
                BindingBuilder
                        .bind(topicQueue1)
                        .to(topicExchange).with("*.booking.*"),
                BindingBuilder
                        .bind(topicQueue2)
                        .to(topicExchange).with("#.error"));
    }
}
