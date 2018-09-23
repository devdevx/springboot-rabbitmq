package com.devdevx.examples.springbootrabbitmq.broadcast.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "broadcast")
public class BroadcastRmqConfig {

    private static final Logger log = LoggerFactory.getLogger(BroadcastRmqConfig.class);

    @Value("${app.rabbitmq.broadcast.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.broadcast.queue-one}")
    private String queueNameOne;

    @Value("${app.rabbitmq.broadcast.queue-two}")
    private String queueNameTwo;

    @Value("${app.rabbitmq.broadcast.queue-three}")
    private String queueNameThree;

    @Bean
    FanoutExchange exchange() {
        log.info("Creating exchange: {}", exchangeName);
        return new FanoutExchange(exchangeName);
    }

    @Bean
    Queue queueOne() {
        log.info("Creating queue: {}", queueNameOne);
        return new Queue(queueNameOne, false);
    }

    @Bean
    Queue queueTwo() {
        log.info("Creating queue: {}", queueNameTwo);
        return new Queue(queueNameTwo, false);
    }

    @Bean
    Queue queueThree() {
        log.info("Creating queue: {}", queueNameThree);
        return new Queue(queueNameThree, false);
    }

    @Bean
    Binding bindingOne(@Qualifier("queueOne") Queue queue, FanoutExchange exchange) {
        log.info("Binding queue '{}' to the exchange '{}'", queue.getName(), exchange.getName());
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding bindingTwo(@Qualifier("queueTwo") Queue queue, FanoutExchange exchange) {
        log.info("Binding queue '{}' to the exchange '{}'", queue.getName(), exchange.getName());
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding bindingThree(@Qualifier("queueThree") Queue queue, FanoutExchange exchange) {
        log.info("Binding queue '{}' to the exchange '{}'", queue.getName(), exchange.getName());
        return BindingBuilder.bind(queue).to(exchange);
    }
}
