package com.demo.project78;

import com.demo.project78.config.AmqpConfig;
import com.demo.project78.exchange.ExchangeSender;
import com.demo.project78.fanout.FanoutSender;
import com.demo.project78.model.Customer;
import com.demo.project78.queue.QueueSender;
import com.demo.project78.rpc.client.RpcClient;
import com.demo.project78.topic.TopicSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner sendData(QueueSender queueSender, ExchangeSender exchangeSender,
                                      FanoutSender fanoutSender, TopicSender topicSender, RpcClient rpcClient) {
        return args -> {
            sendDataToSimpleQueue(queueSender);
            sendDataToDirectExchange(exchangeSender);
            sendDataToFanoutExchange(fanoutSender);
            sendDataToTopicExchange(topicSender);
            sendDataToTestError(queueSender);
            sendRpcCall(rpcClient);
        };
    }

    private void sendDataToSimpleQueue(QueueSender queueSender) {
        Customer customer = Customer.builder()
                .name("Jack")
                .age(35)
                .build();
        //Simple object sent to queue
        queueSender.send(customer);
        System.out.println();
    }

    private void sendDataToDirectExchange(ExchangeSender exchangeSender) {
        Customer customer = Customer.builder()
                .name("Adam")
                .age(40)
                .build();
        //Will be received by the queue
        exchangeSender.send(customer, AmqpConfig.DIRECT_KEY);
        //Will not be received by any queue
        exchangeSender.send(customer, "");
        System.out.println();
    }

    private void sendDataToFanoutExchange(FanoutSender fanoutSender) {
        Customer customer = Customer.builder()
                .name("Raj")
                .age(30)
                .build();
        //All queue registered will receive message irrespective of routing key
        fanoutSender.send(customer, AmqpConfig.FANOUT_KEY1);
        fanoutSender.send(customer, AmqpConfig.FANOUT_KEY2);
        System.out.println();
    }

    private void sendDataToTopicExchange(TopicSender topicSender) {
        Customer customer = Customer.builder()
                .name("David")
                .age(32)
                .build();
        //Based on routing key message is delivered to right queue
        topicSender.send(customer, "status.booking.confirmed");
        topicSender.send(customer, "status.booking.error");
        System.out.println();
    }

    private void sendDataToTestError(QueueSender queueSender) {
        Customer customer = Customer.builder()
                .name("NO_NAME")
                .age(35)
                .build();
        queueSender.send(customer);
    }

    private void sendRpcCall(RpcClient rpcClient) {
        Customer customer = Customer.builder()
                .name("Adam")
                .age(40)
                .build();
        rpcClient.send(customer, AmqpConfig.RPC_KEY);
        System.out.println();
    }

}
