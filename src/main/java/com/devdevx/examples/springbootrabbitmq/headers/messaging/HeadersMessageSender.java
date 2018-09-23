package com.devdevx.examples.springbootrabbitmq.headers.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile("producer")
@Component
@ConditionalOnProperty(name = "app.rabbitmq.example", havingValue = "headers")
public class HeadersMessageSender {

    private static final Logger log = LoggerFactory.getLogger(HeadersMessageSender.class);

    private RabbitTemplate rabbitTemplate;

    private int id = 0;
    private Random random = new Random();
    private String[] priorities = {"high", "normal"};
    private String[] clients = {"premium", "basic"};

    public HeadersMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelayString = "${app.rabbitmq.delay-ms}")
    public void send() {
        String priority = priorities[random.nextInt(priorities.length)];
        String client = clients[random.nextInt(clients.length)];
        HeadersMessage message = new HeadersMessage(id++, priority + " " + client);
        log.info("Sending message: {} with headers: priority='{}', client='{}'", message, priority, client);
        rabbitTemplate.convertAndSend(message, m -> {
            m.getMessageProperties().getHeaders().put("priority", priority);
            m.getMessageProperties().getHeaders().put("client", client);
            return m;
        });
    }
}
