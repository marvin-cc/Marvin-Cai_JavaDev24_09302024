package com.crypto.util;

import com.crypto.dao.SecurityDefinition;
import com.crypto.data.Order;
import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PositionLoader {

    public static List<Order> load() {
        String path = "./position.csv";
        List<Order> orders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            // skip title line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length == 2) {
                    String ticker = values[0].trim();
                    Long quantity = Long.parseLong(values[1].trim());

                    orders.add(new Order(new SecurityDefinition(null, ticker, null, null, null, null), quantity, new BigDecimal(0)));
                } else {
                    System.err.println("Invalid position format: " + line);
                }
            }
            return orders;
        } catch (IOException e) {
            System.err.println("Error loading position: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}