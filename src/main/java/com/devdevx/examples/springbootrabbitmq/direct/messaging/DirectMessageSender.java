package com.devdevx.examples.springbootrabbitmq.direct.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Profile("producer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "direct")
public class DirectMessageSender {

    private static final Logger log = LoggerFactory.getLogger(DirectMessageSender.class);

    @Value("${app.rabbitmq.direct.routing-key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private int id = 0;

    public DirectMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        DirectMessage message = new DirectMessage(id++, LocalDateTime.now().toString(), new Random().nextInt(20) * 1000);
        log.info("Sending direct message to '{}': {}", routingKey, message);
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}
