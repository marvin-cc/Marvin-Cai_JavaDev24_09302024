package com.crypto.util.event;

import com.crypto.data.Order;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class PositionLoaded extends ApplicationEvent {

    private List<Order> orders;

    public PositionLoaded(Object source, List<Order> orders) {
        super(source);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
