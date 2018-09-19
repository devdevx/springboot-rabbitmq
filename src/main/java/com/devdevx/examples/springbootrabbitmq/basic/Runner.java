package com.devdevx.examples.springbootrabbitmq.basic;

import com.devdevx.examples.springbootrabbitmq.basic.messaging.BasicMessageSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("producer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "basic")
public class Runner implements CommandLineRunner {

    // TODO change to schedule
    private final BasicMessageSender messageSender;

    public Runner(BasicMessageSender messageSender) {
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
