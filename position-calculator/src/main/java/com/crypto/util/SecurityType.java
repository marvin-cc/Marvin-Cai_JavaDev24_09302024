package com.crypto.util;

public enum SecurityType {
    STOCK("stock"),
    CALL("call"),
    PUT("put");

    private final String type;

    SecurityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
