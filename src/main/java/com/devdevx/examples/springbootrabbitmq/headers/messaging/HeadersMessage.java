package com.devdevx.examples.springbootrabbitmq.headers.messaging;

public class HeadersMessage {
    private Integer id;
    private String message;

    public HeadersMessage() {
    }

    public HeadersMessage(Integer id, String message) {
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
        return "HeadersMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
