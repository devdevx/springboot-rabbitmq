package com.devdevx.examples.springbootrabbitmq.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Profile("producer")
@Component
public class MessageSender {

    @Value("${app.rabbitmq.key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;
    private int n = 0;

    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        Message message = new Message(LocalDateTime.now().toString() + " " + n++, new Random().nextInt(20) * 1000);
        System.out.println("Sending msg");
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}
