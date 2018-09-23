package com.devdevx.examples.springbootrabbitmq.headers.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "headers")
public class HeadersRmqConfig {

    private static final Logger log = LoggerFactory.getLogger(HeadersRmqConfig.class);

    @Value("${app.rabbitmq.headers.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.headers.queue-fast}")
    private String queueFast;

    @Value("${app.rabbitmq.headers.queue-slow}")
    private String queueSlow;

    @Bean
    HeadersExchange exchange() {
        log.info("Creating exchange: {}", exchangeName);
        return new HeadersExchange(exchangeName);
    }

    @Bean
    Queue queueFast() {
        log.info("Creating queue: {}", queueFast);
        return new Queue(queueFast, false);
    }

    @Bean
    Queue queueSlow() {
        log.info("Creating queue: {}", queueSlow);
        return new Queue(queueSlow, false);
    }

    @Bean
    Binding bindingFast(@Qualifier("queueFast") Queue queue, HeadersExchange exchange) {
        Map<String, Object> absolutePriority = new HashMap<>();
        absolutePriority.put("priority", "high");
        absolutePriority.put("client", "premium");
        log.info("Binding queue '{}' to the exchange '{}' with all match '{}'", queueFast, exchangeName, absolutePriority);
        return BindingBuilder.bind(queue).to(exchange).whereAll(absolutePriority).match();
    }

    @Bean
    Binding bindingSlow(@Qualifier("queueSlow") Queue queue, HeadersExchange exchange) {
        Map<String, Object> otherPriority = new HashMap<>();
        otherPriority.put("priority", "normal");
        otherPriority.put("client", "basic");
        log.info("Binding queue '{}' to the exchange '{}' with any match '{}'", queueSlow, exchangeName, otherPriority);
        return BindingBuilder.bind(queue).to(exchange).whereAny(otherPriority).match();
    }
}
