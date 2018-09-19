package com.devdevx.examples.springbootrabbitmq.broadcast.messaging;

public class BroadcastMessage {
    private Integer id;
    private String message;
    private Integer ms;

    public BroadcastMessage() {
    }

    public BroadcastMessage(Integer id, String message, Integer ms) {
        this.id = id;
        this.message = message;
        this.ms = ms;
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

    public Integer getMs() {
        return ms;
    }

    public void setMs(Integer ms) {
        this.ms = ms;
    }

    @Override
    public String toString() {
        return "BroadcastMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", ms=" + ms +
                '}';
    }
}
