package com.devdevx.examples.springbootrabbitmq.broadcast.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "broadcast")
public class BroadcastMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(BroadcastMessageReceiver.class);

    @Value("${app.rabbitmq.broadcast.queue-one}")
    private String queueNameOne;

    @Value("${app.rabbitmq.broadcast.queue-two}")
    private String queueNameTwo;

    @Value("${app.rabbitmq.broadcast.queue-three}")
    private String queueNameThree;

    @RabbitListener(queues = "${app.rabbitmq.broadcast.queue-one}")
    public void receiveOne(BroadcastMessage message) {
        log.info("Message received on queue '{}' : {}", queueNameOne, message);
    }

    @RabbitListener(queues = "${app.rabbitmq.broadcast.queue-two}")
    public void receiveTwo(BroadcastMessage message) {
        log.info("Message received on queue '{}' : {}", queueNameTwo, message);
    }

    @RabbitListener(queues = "${app.rabbitmq.broadcast.queue-three}")
    public void receiveThree(BroadcastMessage message) {
        log.info("Message received on queue '{}' : {}", queueNameThree, message);
    }
}
