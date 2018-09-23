package com.devdevx.examples.springbootrabbitmq.headers.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("producer")
@Configuration
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "headers")
public class HeadersRmqProduConfig {

    @Value("${app.rabbitmq.headers.exchange}")
    private String exchangeName;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        rabbitTemplate.setExchange(exchangeName);
        return rabbitTemplate;
    }

}
