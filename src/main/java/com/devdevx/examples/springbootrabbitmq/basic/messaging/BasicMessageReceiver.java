package com.devdevx.examples.springbootrabbitmq.basic.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "basic")
public class BasicMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(BasicMessageReceiver.class);

    @Value("${app.rabbitmq.basic.queue}")
    private String queueName;

    @RabbitListener(queues = "${app.rabbitmq.basic.queue}")
    public void receive(BasicMessage message) {
        log.info("Message received on queue '{}' : {}", queueName, message);
    }
}
