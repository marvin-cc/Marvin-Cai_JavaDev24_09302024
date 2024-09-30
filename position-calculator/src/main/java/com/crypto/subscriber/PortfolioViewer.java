package com.crypto.subscriber;

import com.crypto.messaging.MessageQueue;
import com.crypto.messaging.PortfolioMessage;
import com.crypto.messaging.Subscriber;
import com.crypto.util.event.PositionLoaded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PortfolioViewer  implements ApplicationListener<PositionLoaded> {
    @Autowired
    private MessageQueue messageQueue;

    @Override
    public void onApplicationEvent(PositionLoaded event) {
        Subscriber subscriber = new Subscriber("portfolio viewer", messageQueue, "PortfolioChange");
        while (true) {
            try {
                // max market data update interval should be 2 second
                PortfolioMessage message = (PortfolioMessage) subscriber.receive(2, TimeUnit.SECONDS);
                if (message != null) {
                    if (message.isStart()) {
                        System.out.println("##Portfolio");
                        System.out.println(String.format("%-30s %-20s %-20s %-20s", "symbol", "price", "qty", "value"));
                    } else {
                        System.out.println(message.getContent());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error receiving portfolio change: " + e);
            }
        }
    }
}
