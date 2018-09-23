package com.devdevx.examples.springbootrabbitmq.direct.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    @Scheduled(fixedDelayString = "${app.rabbitmq.delay-ms}")
    public void send() {
        DirectMessage message = new DirectMessage(id++, LocalDateTime.now().toString());
        log.info("Sending message to '{}': {}", routingKey, message);
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}
