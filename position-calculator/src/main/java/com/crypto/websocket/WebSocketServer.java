package com.crypto.websocket;

import org.springframework.stereotype.Component;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;

@Component
@ServerEndpoint("/ws")
public class WebSocketServer {

    private static Set<Session> sessions = new HashSet<>();


    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }


    public void sendToAllClient(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}