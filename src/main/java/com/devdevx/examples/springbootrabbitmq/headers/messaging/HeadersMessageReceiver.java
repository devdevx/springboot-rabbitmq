package com.devdevx.examples.springbootrabbitmq.headers.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "headers")
public class HeadersMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(HeadersMessageReceiver.class);

    @Value("${app.rabbitmq.headers.queue-slow}")
    private String queueSlow;

    @Value("${app.rabbitmq.headers.queue-fast}")
    private String queueFast;

    @RabbitListener(queues = "${app.rabbitmq.headers.queue-slow}")
    public void receiveFast(HeadersMessage message) {
        log.info("Message received on queue '{}' : {}", queueSlow, message);
    }

    @RabbitListener(queues = "${app.rabbitmq.headers.queue-fast}")
    public void receiveSlow(HeadersMessage message) {
        log.info("Message received on queue '{}' : {}", queueFast, message);
    }
}
