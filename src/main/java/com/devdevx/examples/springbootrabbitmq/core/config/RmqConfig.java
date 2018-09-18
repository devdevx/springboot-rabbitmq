package com.devdevx.examples.springbootrabbitmq.core.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RmqConfig {

    @Value("${app.rabbitmq.url:}")
    String rabbitmqUrl;

    @Bean
    Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Profile("heroku")
    RabbitProperties properties(RabbitProperties properties) {
        // amqp://<USER>:<PASSWORD>@<HOST>/<VHOST>
        String[] tokens = rabbitmqUrl.split("amqp://|:|@|/");
        properties.setHost(tokens[3]);
        properties.setUsername(tokens[1]);
        properties.setPassword(tokens[2]);
        properties.setVirtualHost(tokens[4]);
        return properties;
    }
}
