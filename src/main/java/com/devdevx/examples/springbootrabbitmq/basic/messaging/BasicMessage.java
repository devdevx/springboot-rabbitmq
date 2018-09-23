package com.devdevx.examples.springbootrabbitmq.basic.messaging;

public class BasicMessage {
    private Integer id;
    private String message;

    public BasicMessage() {
    }

    public BasicMessage(Integer id, String message) {
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
        return "BasicMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
