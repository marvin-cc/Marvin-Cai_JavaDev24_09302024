package com.crypto.messaging;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessageQueue {
    private final Map<String, List<Subscriber>> subscribers = new ConcurrentHashMap<>();

    public void subscribe(String topic, Subscriber subscriber) {
        subscribers.computeIfAbsent(topic, k -> new ArrayList<>()).add(subscriber);
    }

    public void publish(String topic, Message message) {
        List<Subscriber> subs = subscribers.getOrDefault(topic, new ArrayList<>());
        subs.forEach(sub -> sub.send(message));
    }
}
