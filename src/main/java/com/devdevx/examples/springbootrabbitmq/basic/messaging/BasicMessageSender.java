package com.devdevx.examples.springbootrabbitmq.basic.messaging;

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
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "basic")
public class BasicMessageSender {

    private static final Logger log = LoggerFactory.getLogger(BasicMessageSender.class);

    @Value("${app.rabbitmq.basic.queue}")
    private String queueName;

    private RabbitTemplate rabbitTemplate;

    private int id = 0;

    public BasicMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelayString = "${app.rabbitmq.delay-ms}")
    public void send() {
        BasicMessage message = new BasicMessage(id++, LocalDateTime.now().toString());
        log.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
