package com.devdevx.examples.springbootrabbitmq.direct;

import com.devdevx.examples.springbootrabbitmq.direct.messaging.DirectMessageSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("producer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "direct")
public class Runner implements CommandLineRunner {

    // TODO change to schedule
    private final DirectMessageSender messageSender;

    public Runner(DirectMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            messageSender.send();
            Thread.sleep(10000);
        }
    }

}
