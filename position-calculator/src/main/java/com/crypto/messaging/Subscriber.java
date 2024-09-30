package com.crypto.messaging;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Subscriber {
    private final String name;
    private final String topic;
    private final LinkedBlockingQueue<Message> messageBuffer = new LinkedBlockingQueue<>();

    public Subscriber(String name, MessageQueue queue, String topic) {
        this.name = name;
        this.topic = topic;
        queue.subscribe(topic, this);
    }

    public void send(Message message) {
        messageBuffer.offer(message);
    }

    public Message receive(long timeout, TimeUnit unit) throws InterruptedException {
        return messageBuffer.poll(timeout, unit);
    }
}