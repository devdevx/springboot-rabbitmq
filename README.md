# Introduction

This project contains multiple examples using the different RabbitMQ exchanges with Spring Boot 2.

Additionally, the project contains all the configuration files that are required to run in Heroku.

# Examples

## Default exchange (`basic`)

The default exchange is a direct exchange with no name.
Every queue is automatically bound to the default exchange with a routing key which is the queue name.

## Fanout exchange (`broadcast`)

The fanout exchange sends the message to all queues that are bound to it.

## Direct exchange (`direct`)

The direct exchange sends the message to the queues that are bind to the exchange with the same routing key that is used to send the message.

## Headers exchange (`headers`)

The headers exchange sends the message to the queues that are bind to the exchange with any/all of the headers of the message.

Te use of this type of exchange is not recommended if you can use the topic exchange, its performance is better.

## Topic exchange (`topic`)

The topic exchange sends the message to the queues that are bind to the exchange with a routing pattern that match the routing key.

In this exchange the routing key is a list of words delimited by periods (.).

The routing patters consists of exact words, asterisks (*) to match a word and hashtags (#) to match zero or more words.

# Exception handling

The default configuration for the unhandled exceptions is requeuing the message. We can change this in the properties (`spring.rabbitmq.listener.simple.default-requeue-rejected`). 

# Running it

## Running locally

### With docker compose

1. Select the example to run changing the value of the property `app.rabbitmq.example` in the file `application-dev.properties`. (Supported values are `basic | broadcast | direct | headers | topic`)
1. Run `docker-compose up` in the root folder.
1. Run `mvnw spring-boot:run -Dspring-boot.run.profiles=producer,dev` in the root folder to start a instance of the producer.
1. Run `mvnw spring-boot:run -Dspring-boot.run.profiles=consumer,dev` in the root folder to start a instance of the consumer.

You can enter in the the rabbit web console going to `http://localhost:15672/` with the credentials `guest:guest`.

### Without docker compose

1. Select the example to run changing the value of the property `app.rabbitmq.example` in the file `application-dev.properties`. (Supported values are `basic | broadcast | direct | headers | topic`)
1. Configure the properties of the RabbitMQ in the file `application-dev.properties`.
1. Run `mvnw spring-boot:run -Dspring-boot.run.profiles=producer,dev` in the root folder to start a instance of the producer.
1. Run `mvnw spring-boot:run -Dspring-boot.run.profiles=consumer,dev` in the root folder to start a instance of the consumer.

## Running on Heroku

To run the example in Heroku, you need the CloudAMQP add-on.

The configuration is defined in the `Procfile`.

The env properties are:

|Property|Definition|Default value|
|--------|----------|-------------|
| CLOUDAMQP_URL | RabbitMQ connection url, added by the add-on | - |
| EXAMPLE_TYPE | The example to run | basic |
| DELAY_MS | The delay between sends in milliseconds | 10000 |