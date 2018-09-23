package com.devdevx.examples.springbootrabbitmq.topic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "topic")
public class TopicRmqConfig {

    private static final Logger log = LoggerFactory.getLogger(TopicRmqConfig.class);

    @Value("${app.rabbitmq.topic.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.topic.creation-queue}")
    private String creationQueueName;

    @Value("${app.rabbitmq.topic.users-queue}")
    private String usersQueueName;

    @Value("${app.rabbitmq.topic.events-queue}")
    private String eventsQueueName;

    @Bean
    TopicExchange exchange() {
        log.info("Creating exchange: {}", exchangeName);
        return new TopicExchange(exchangeName);
    }

    @Bean
    Queue creationQueue() {
        log.info("Creating queue: {}", creationQueueName);
        return new Queue(creationQueueName, false);
    }

    @Bean
    Queue usersQueue() {
        log.info("Creating queue: {}", usersQueueName);
        return new Queue(usersQueueName, false);
    }

    @Bean
    Queue eventsQueue() {
        log.info("Creating queue: {}", eventsQueueName);
        return new Queue(eventsQueueName, false);
    }

    @Bean
    Binding bindingCreation(@Qualifier("creationQueue") Queue queue, TopicExchange exchange) {
        log.info("Binding queue '{}' to the exchange '{}'", queue.getName(), exchange.getName());
        return BindingBuilder.bind(queue).to(exchange).with("*.created.#");
    }

    @Bean
    Binding bindingUsers(@Qualifier("usersQueue") Queue queue, TopicExchange exchange) {
        log.info("Binding queue '{}' to the exchange '{}'", queue.getName(), exchange.getName());
        return BindingBuilder.bind(queue).to(exchange).with("user.#");
    }

    @Bean
    Binding bindingEvents(@Qualifier("eventsQueue") Queue queue, TopicExchange exchange) {
        log.info("Binding queue '{}' to the exchange '{}'", queue.getName(), exchange.getName());
        return BindingBuilder.bind(queue).to(exchange).with("#");
    }
}
