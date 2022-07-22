package com.demo.project78.fanout;

import com.demo.project78.config.AmqpConfig;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class FanoutExchangeConfig {

    @Bean
    public Declarables fanoutBindings() {
        Queue fanoutQueue1 = new Queue(AmqpConfig.FANOUT_QUEUE_1, false);
        Queue fanoutQueue2 = new Queue(AmqpConfig.FANOUT_QUEUE_2, false);
        FanoutExchange fanoutExchange = new FanoutExchange(AmqpConfig.FANOUT_EXCHANGE);

        return new Declarables(
                fanoutQueue1,
                fanoutQueue2,
                fanoutExchange,
                bind(fanoutQueue1).to(fanoutExchange),
                BindingBuilder.bind(fanoutQueue2).to(fanoutExchange));
    }
}
