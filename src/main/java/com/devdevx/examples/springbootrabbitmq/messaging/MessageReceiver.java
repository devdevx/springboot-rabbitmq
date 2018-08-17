package com.devdevx.examples.springbootrabbitmq.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
public class MessageReceiver {

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receive(Message message) {
        try {
            Thread.sleep(message.getMs());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(message);
    }
}
