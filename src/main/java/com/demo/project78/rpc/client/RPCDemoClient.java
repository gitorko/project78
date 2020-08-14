package com.demo.project78.rpc.client;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.demo.project78.rpc.client")
public class RPCDemoClient implements CommandLineRunner {

    public static final String directExchangeName = "rpc-exchange";
    public static final String routingKey = "rpc";

    @Autowired
    RpcClient rpcClient;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchangeName);
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RPCDemoClient.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        rpcClient.send("John");
    }

}

@Component
class RpcClient {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    public void send(String name) {
        System.out.println("Sending name: " + name);
        template.setReplyTimeout(60000);
        String response = (String) template.convertSendAndReceive(exchange.getName(), RPCDemoClient.routingKey, name);
        System.out.println("Got '" + response + "'");
    }
}