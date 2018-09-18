package com.devdevx.examples.springbootrabbitmq.basic.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
public class BasicMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(BasicMessageReceiver.class);

    @RabbitListener(queues = "${app.rabbitmq.basic.queue}")
    public void receive(BasicMessage message) {
        log.info("Basic message received {}", message);
        try {
            log.info("Sleeping {}ms", message.getMs());
            Thread.sleep(message.getMs());
        } catch (InterruptedException e) {
            log.error("Sleeping error", e);
        }
    }
}
