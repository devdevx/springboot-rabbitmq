package com.devdevx.examples.springbootrabbitmq.broadcast.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    @Scheduled(fixedDelayString = "${app.rabbitmq.delay-ms}")
    public void send() {
        BroadcastMessage message = new BroadcastMessage(id++, LocalDateTime.now().toString());
        log.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(message);
    }
}
