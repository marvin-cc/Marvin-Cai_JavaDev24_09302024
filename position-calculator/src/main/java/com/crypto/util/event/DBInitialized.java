package com.crypto.util.event;

import org.springframework.context.ApplicationEvent;

public class DBInitialized extends ApplicationEvent {

    public DBInitialized(Object source) {
        super(source);
    }
}
