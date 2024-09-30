package com.crypto.messaging;

public class PortfolioMessage implements Message {
    private final boolean start;

    private final String content;

    public PortfolioMessage(boolean start, String content) {
        this.start = start;
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    public boolean isStart() {
        return start;
    }
}
