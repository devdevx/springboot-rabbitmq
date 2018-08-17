package com.devdevx.examples.springbootrabbitmq.messaging.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
public class RmqConsuConfig {

}
