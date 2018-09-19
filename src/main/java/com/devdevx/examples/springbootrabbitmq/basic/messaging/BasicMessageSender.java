package com.devdevx.examples.springbootrabbitmq.basic.messaging;

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
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "basic")
public class BasicMessageSender {

    private static final Logger log = LoggerFactory.getLogger(BasicMessageSender.class);

    private RabbitTemplate rabbitTemplate;

    private int id = 0;

    public BasicMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        BasicMessage message = new BasicMessage(id++, LocalDateTime.now().toString(), new Random().nextInt(20) * 1000);
        log.info("Sending basic message: {}", message);
        rabbitTemplate.convertAndSend(message);
    }
}
