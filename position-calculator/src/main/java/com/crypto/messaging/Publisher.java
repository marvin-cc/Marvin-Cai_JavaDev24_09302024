package com.crypto.messaging;

public class Publisher {
    private final MessageQueue queue;

    public Publisher(MessageQueue queue) {
        this.queue = queue;
    }

    public void send(String topic, Message message) {
        queue.publish(topic, message);
    }
}
