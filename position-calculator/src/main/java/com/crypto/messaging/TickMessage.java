package com.crypto.messaging;

public class TickMessage implements Message {
    private final String content;

    public TickMessage(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}