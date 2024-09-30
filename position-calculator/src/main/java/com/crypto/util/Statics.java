package com.crypto.util;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
public class Statics {
    // Generate Mu, Sigma and Initial price for selected symbols
    public static final Map<String, Double> MU = new HashMap<>();

    public static final Map<String, Double> SIGMA = new HashMap<>();

    public static final Map<String, Double> INITIAL_PRICE = new HashMap<>();


    static {
        Random random = new Random(System.currentTimeMillis());
        MU.put("AAPL", random.nextDouble());
        MU.put("TSLA", random.nextDouble());
        MU.put("MSFT", random.nextDouble());
        MU.put("AMZN", random.nextDouble());

        SIGMA.put("AAPL", random.nextDouble());
        SIGMA.put("TSLA", random.nextDouble());
        SIGMA.put("MSFT", random.nextDouble());
        SIGMA.put("AMZN", random.nextDouble());

        INITIAL_PRICE.put("AAPL", 109d);
        INITIAL_PRICE.put("TSLA", 200d);
        INITIAL_PRICE.put("MSFT", 300d);
        INITIAL_PRICE.put("AMZN", 180d);
    }
}
