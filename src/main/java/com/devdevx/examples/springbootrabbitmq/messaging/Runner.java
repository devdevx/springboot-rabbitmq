package com.devdevx.examples.springbootrabbitmq.messaging;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("producer")
@Component
public class Runner implements CommandLineRunner {

    private final MessageSender messageSender;

    public Runner(MessageSender messageSender) {
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
