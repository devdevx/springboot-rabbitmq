package com.devdevx.examples.springbootrabbitmq.broadcast.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Profile("producer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "broadcast")
public class BroadcastMessageSender {

    private static final Logger log = LoggerFactory.getLogger(BroadcastMessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    private int id = 0;

    public BroadcastMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        BroadcastMessage message = new BroadcastMessage(id++, LocalDateTime.now().toString(), new Random().nextInt(3) * 1000);
        log.info("Sending broadcast message: {}", message);
        rabbitTemplate.convertAndSend(message);
    }
}
