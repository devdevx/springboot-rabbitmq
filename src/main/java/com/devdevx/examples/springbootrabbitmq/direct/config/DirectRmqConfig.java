package com.devdevx.examples.springbootrabbitmq.direct.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "direct")
public class DirectRmqConfig {

    private static final Logger log = LoggerFactory.getLogger(DirectRmqConfig.class);

    @Value("${app.rabbitmq.direct.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.direct.queue}")
    private String queueName;

    @Value("${app.rabbitmq.direct.routing-key}")
    private String routingKey;

    @Bean
    DirectExchange exchange() {
        log.info("Creating exchange: {}", exchangeName);
        return new DirectExchange(exchangeName);
    }

    @Bean
    Queue queue() {
        log.info("Creating queue: {}", queueName);
        return new Queue(queueName, false);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        log.info("Binding queue '{}' to the exchange '{}' with routing key '{}'", queueName, exchangeName, routingKey);
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}
