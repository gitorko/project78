package com.demo.project78.rpc.server;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.demo.project78.rpc.server")
public class RPCDemoServer implements CommandLineRunner {
    public static final String directExchangeName = "rpc-exchange";
    public static final String queueName = "rpc-queue";
    public static final String routingKey = "rpc";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RPCDemoServer.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Waiting...");
        new Scanner(System.in).nextLine();

    }
}

@Component
class RpcServer {

    @SneakyThrows
    @RabbitListener(queues = RPCDemoServer.queueName)
    public String LongRunningTask(String name) {
        System.out.println("Received: " + name);
        //Long running processing
        TimeUnit.SECONDS.sleep(15);
        return "Hello world, " + name;
    }

}
