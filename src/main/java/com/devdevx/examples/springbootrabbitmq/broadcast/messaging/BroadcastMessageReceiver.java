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
        log.info("Broadcast message received on queue '{}' : {}", queueNameOne, message);
        try {
            log.info("Sleeping {}ms", message.getMs());
            Thread.sleep(message.getMs());
        } catch (InterruptedException e) {
            log.error("Sleeping error", e);
        }
    }

    @RabbitListener(queues = "${app.rabbitmq.broadcast.queue-two}")
    public void receiveTwo(BroadcastMessage message) {
        log.info("Broadcast message received on queue '{}' : {}", queueNameTwo, message);
        try {
            log.info("Sleeping {}ms", message.getMs());
            Thread.sleep(message.getMs());
        } catch (InterruptedException e) {
            log.error("Sleeping error", e);
        }
    }

    @RabbitListener(queues = "${app.rabbitmq.broadcast.queue-three}")
    public void receiveThree(BroadcastMessage message) {
        log.info("Broadcast message received on queue '{}' : {}", queueNameThree, message);
        try {
            log.info("Sleeping {}ms", message.getMs());
            Thread.sleep(message.getMs());
        } catch (InterruptedException e) {
            log.error("Sleeping error", e);
        }
    }
}
