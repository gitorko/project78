package com.demo.project78;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class QueueDemo implements CommandLineRunner {

    @Autowired
    MessageSender messageSender;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(QueueDemo.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        messageSender.send();
    }

    @Bean
    public Queue hello() {
        return new Queue("queue2", false);
    }

}

@Component
class MessageSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void send() {
        String message = "Hello World!";
        template.convertAndSend(queue.getName(), message);
        System.out.println("Sent '" + message + "'");
    }
}

@RabbitListener(queues = "queue2")
class MessageReceiver {

    @RabbitHandler
    public void receive(String in) {
        System.out.println("Received '" + in + "'");
    }
}
