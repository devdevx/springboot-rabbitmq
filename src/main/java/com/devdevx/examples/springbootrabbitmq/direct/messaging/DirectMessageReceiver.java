package com.devdevx.examples.springbootrabbitmq.direct.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "direct")
public class DirectMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(DirectMessageReceiver.class);

    @Value("${app.rabbitmq.direct.queue}")
    private String queueName;

    @RabbitListener(queues = "${app.rabbitmq.direct.queue}")
    public void receive(DirectMessage message) {
        log.info("Message received on queue '{}' : {}", queueName, message);
    }
}
