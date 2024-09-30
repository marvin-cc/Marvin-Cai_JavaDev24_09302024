package com.crypto.calculator;

import com.crypto.data.Order;
import com.crypto.messaging.MessageQueue;
import com.crypto.messaging.PortfolioMessage;
import com.crypto.messaging.Subscriber;
import com.crypto.messaging.TickMessage;
import com.crypto.util.event.PositionLoaded;
import com.crypto.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class PositionCalculator implements ApplicationListener<PositionLoaded> {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageQueue messageQueue;

    @Autowired
    private WebSocketServer webSocketServer;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void onApplicationEvent(PositionLoaded event) {
        executor.submit(() -> {
            Subscriber subscriber = new Subscriber("position calculator", messageQueue, "PriceUpdate");
            while (true) {
                try {
                    // max market data update interval should be 2 second
                    TickMessage message = (TickMessage) subscriber.receive(2, TimeUnit.SECONDS);
                    if (message == null) {
                        System.out.println("No price update yet, wait for next tick");
                    } else {
                        calculateCurrentSecurityPrice(event.getOrders(), message.getContent());
                    }
                } catch (Exception e) {
                    System.out.println("Error receiving price update: " + e);
                }
            }
        });
    }

    public void calculateCurrentSecurityPrice(List<Order> orders, String batchPriceUpdate) {
        String[] priceUpdates = batchPriceUpdate.split("/");
        System.out.println();
        for (String priceUpdate : priceUpdates) {
            String[] parts = priceUpdate.split(":");
            System.out.println(String.format("# %s received price update,price changed to %s", parts[0], parts[1]));
            for (Order order : orders) {
                if (order.getSecurityDefinition().getTicker().startsWith(parts[0])) {
                    order.updatePrice(parts[0], Double.valueOf(parts[1]));
                }
            }
        }
        printPortfolio(orders);
    }

    public void printPortfolio(List<Order> orders) {
        messageQueue.publish("PortfolioChange", new PortfolioMessage(true, ""));

        //System.out.println("##Portfolio");
        //System.out.println(String.format("%-30s|%-20s|%-20s|%-20s", "symbol", "price", "qty", "value"));

        BigDecimal portfolioValue = new BigDecimal(0);
        for (Order order : orders) {
            //System.out.println(order);
            messageQueue.publish("PortfolioChange", new PortfolioMessage(false, order.toString()));
            portfolioValue = portfolioValue.add(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        }
        messageQueue.publish("PortfolioChange", new PortfolioMessage(false, "##Portfolio value                                                   " + portfolioValue));
        //System.out.println("##Portfolio value                                                   " + portfolioValue);
        //System.out.println();
    }

    public static double calculateYearToMature(Date maturityDate, Date now) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(maturityDate);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(now);

        int maturityYear = cal1.get(Calendar.YEAR);
        int currentYear = cal2.get(Calendar.YEAR);

        return Math.abs(maturityYear - currentYear);
    }
}
