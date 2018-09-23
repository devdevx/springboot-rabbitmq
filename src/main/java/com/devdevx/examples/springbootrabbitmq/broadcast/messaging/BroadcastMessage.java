package com.devdevx.examples.springbootrabbitmq.broadcast.messaging;

public class BroadcastMessage {
    private Integer id;
    private String message;

    public BroadcastMessage() {
    }

    public BroadcastMessage(Integer id, String message) {
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
        return "BroadcastMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
