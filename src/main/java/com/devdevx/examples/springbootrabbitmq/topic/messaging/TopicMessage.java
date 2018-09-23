package com.devdevx.examples.springbootrabbitmq.topic.messaging;

public class TopicMessage {
    private Integer id;
    private String message;

    public TopicMessage() {
    }

    public TopicMessage(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TopicMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
