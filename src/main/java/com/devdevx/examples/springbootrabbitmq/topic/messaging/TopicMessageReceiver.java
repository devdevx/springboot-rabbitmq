package com.devdevx.examples.springbootrabbitmq.topic.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "topic")
public class TopicMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(TopicMessageReceiver.class);

    @Value("${app.rabbitmq.topic.creation-queue}")
    private String creationQueueName;

    @Value("${app.rabbitmq.topic.users-queue}")
    private String usersQueueName;

    @Value("${app.rabbitmq.topic.events-queue}")
    private String eventsQueueName;

    @RabbitListener(queues = "${app.rabbitmq.topic.creation-queue}")
    public void receiveCreation(TopicMessage message) {
        log.info("Message received on queue '{}' : {}", creationQueueName, message);
    }

    @RabbitListener(queues = "${app.rabbitmq.topic.users-queue}")
    public void receiveUser(TopicMessage message) {
        log.info("Message received on queue '{}' : {}", usersQueueName, message);
    }

    @RabbitListener(queues = "${app.rabbitmq.topic.events-queue}")
    public void receiveEvent(TopicMessage message) {
        log.info("Message received on queue '{}' : {}", eventsQueueName, message);
    }
}
