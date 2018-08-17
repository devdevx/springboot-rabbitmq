package com.devdevx.examples.springbootrabbitmq.messaging;

public class Message {
    private String message;
    private Integer ms;

    public Message() {
    }

    public Message(String message, Integer ms) {
        this.message = message;
        this.ms = ms;
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
        return "Message{" +
                "message='" + message + '\'' +
                ", ms=" + ms +
                '}';
    }
}
