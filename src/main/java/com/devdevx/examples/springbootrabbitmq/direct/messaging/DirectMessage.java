package com.devdevx.examples.springbootrabbitmq.direct.messaging;

public class DirectMessage {
    private Integer id;
    private String message;

    public DirectMessage() {
    }

    public DirectMessage(Integer id, String message) {
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
        return "DirectMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
