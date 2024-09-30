package com.crypto.data;

import com.crypto.messaging.MessageQueue;
import com.crypto.messaging.TickMessage;
import com.crypto.util.Statics;
import com.crypto.util.event.PositionLoaded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MarketDataProvider implements ApplicationListener<PositionLoaded> {

    @Autowired
    private MessageQueue messageQueue;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void onApplicationEvent(PositionLoaded event) {
        Long start = System.currentTimeMillis();
        executor.submit(() -> {
            while (true) {
                try {
                    // sleep for 0.5 - 2 second
                    Thread.sleep(random.nextInt(1500) + 500);
                    Long now = System.currentTimeMillis();
                    StringBuilder batchUpdate = new StringBuilder();
                    // Use Math.Random nextGaussian to simulate random variable drawn from a standardized normal distribution for calculating discrete time geometric Brownian motion
                    if (random.nextBoolean()) {
                        batchUpdate.append("AAPL:" + geometricBrownianStockPrice(Statics.INITIAL_PRICE.get("AAPL"), (now - start) / 1000, Statics.MU.get("AAPL"), Statics.SIGMA.get("AAPL"), random.nextGaussian()) + "/");
                    }
                    if (random.nextBoolean()) {
                        batchUpdate.append("MSFT:" + geometricBrownianStockPrice(Statics.INITIAL_PRICE.get("MSFT"), (now - start) / 1000, Statics.MU.get("MSFT"), Statics.SIGMA.get("MSFT"), random.nextGaussian()) + "/");
                    }
                    if (random.nextBoolean()) {
                        batchUpdate.append("AMZN:" + geometricBrownianStockPrice(Statics.INITIAL_PRICE.get("AMZN"), (now - start) / 1000, Statics.MU.get("AMZN"), Statics.SIGMA.get("AMZN"), random.nextGaussian()) + "/");
                    }
                    if (random.nextBoolean()) {
                        batchUpdate.append("TSLA:" + geometricBrownianStockPrice(Statics.INITIAL_PRICE.get("TSLA"), (now - start) / 1000, Statics.MU.get("TSLA"), Statics.SIGMA.get("TSLA"), random.nextGaussian()) + "/");
                    }

                    if (batchUpdate.length() > 0) {
                        messageQueue.publish("PriceUpdate", new TickMessage(batchUpdate.substring(0, batchUpdate.length() - 1)));
                    }
                } catch (Exception e) {
                    System.out.println("Market data provider fail to generate tick: " + e);
                }
            }
        });
    }

    public double geometricBrownianStockPrice(double S, long t, double mu, double sigma, double elipson) {
        return S + S * ((mu * t / 7257600d) + sigma * elipson * Math.sqrt(t / 7257600d));
    }
}
