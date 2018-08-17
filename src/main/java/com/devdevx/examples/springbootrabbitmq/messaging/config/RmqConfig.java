package com.devdevx.examples.springbootrabbitmq.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
public class RmqConfig {

    @Value("${app.rabbitmq.topic}")
    private String topicExchangeName;

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Value("${app.rabbitmq.key}")
    private String routingKey;

    @Value("${app.rabbitmq.url}")
    String rabbitmqUrl;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Profile("heroku")
    RabbitProperties properties(RabbitProperties properties) {
        // amqp://USER:PASSWORD@HOST/VHOST
        String[] tokens = rabbitmqUrl.split("amqp://|:|@|/");
        properties.setHost(tokens[3]);
        properties.setUsername(tokens[1]);
        properties.setPassword(tokens[2]);
        properties.setVirtualHost(tokens[4]);
        return properties;
    }
}
