package com.crypto.app;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class App {

    private Session session;

    public static void main(String[] args) {
        try {
            App client = new App();
            client.connectToServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectToServer() throws Exception {
        URI uri = new URI("ws://localhost:12345/websocket-endpoint");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, uri);
    }
}