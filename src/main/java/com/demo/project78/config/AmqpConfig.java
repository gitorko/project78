package com.demo.project78.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

@Configuration
@Slf4j
public class AmqpConfig {

    public static final String SIMPLE_QUEUE = "simple.queue";

    public static final String DIRECT_EXCHANGE = "direct.exchange";
    public static final String DIRECT_QUEUE = "direct.queue";
    public static final String DIRECT_KEY = "direct-key";

    public static final String RPC_EXCHANGE = "rpc.exchange";
    public static final String RPC_QUEUE = "rpc.queue";
    public static final String RPC_KEY = "rpc-key";

    public static final String FANOUT_QUEUE_1 = "fanout.queue1";
    public static final String FANOUT_QUEUE_2 = "fanout.queue2";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String FANOUT_KEY1 = "*.fan-key1.*";
    public static final String FANOUT_KEY2 = "*.fan-key2.*";

    public static final String TOPIC_QUEUE_1 = "topic.booking";
    public static final String TOPIC_QUEUE_2 = "topic.error";
    public static final String TOPIC_EXCHANGE = "topic.exchange";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * Without setting the error handle the failed message getting re-queued will cause infinite loops
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setErrorHandler(errorHandler());
        return factory;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
    }

    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
        @Override
        public boolean isFatal(Throwable t) {
            if (t instanceof ListenerExecutionFailedException) {
                log.error("Fatal error: {}", t.getMessage());
                return false;
            }
            return super.isFatal(t);
        }
    }
}

