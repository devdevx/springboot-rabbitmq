package com.devdevx.examples.springbootrabbitmq.basic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "basic")
public class BasicRmqConfig {

    private static final Logger log = LoggerFactory.getLogger(BasicRmqConfig.class);

    @Value("${app.rabbitmq.basic.queue}")
    private String queueName;

    @Bean
    Queue queue() {
        log.info("Creating queue: {}", queueName);
        return new Queue(queueName, false);
    }
}
