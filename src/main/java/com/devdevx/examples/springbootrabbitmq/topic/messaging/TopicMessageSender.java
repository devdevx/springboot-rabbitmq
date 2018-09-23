package com.devdevx.examples.springbootrabbitmq.topic.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile("producer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "topic")
public class TopicMessageSender {

    private static final Logger log = LoggerFactory.getLogger(TopicMessageSender.class);

    private RabbitTemplate rabbitTemplate;

    private int id = 0;
    private Random random = new Random();
    private String[] routingKeys = {"user.created", "user.updated", "profile.created", "external.api.error"};

    public TopicMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelayString = "${app.rabbitmq.delay-ms}")
    public void send() {
        String routingKey = routingKeys[random.nextInt(routingKeys.length)];
        TopicMessage message = new TopicMessage(id++, routingKey);
        log.info("Sending message: {} with routing key {}", message, routingKey);
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}
