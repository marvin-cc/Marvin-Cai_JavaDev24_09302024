package com.crypto.calculator;

import com.crypto.dao.SecurityDefinition;
import com.crypto.data.Order;
import com.crypto.repositories.SecurityRepository;
import com.crypto.util.PositionLoader;
import com.crypto.util.SecurityType;
import com.crypto.util.Statics;
import com.crypto.util.event.DBInitialized;
import com.crypto.util.event.PositionLoaded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PositionInitializer implements ApplicationListener<DBInitialized> {

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void onApplicationEvent(DBInitialized event) {
        List<Order> orders = PositionLoader.load();

        List<Order> initialized = orders.stream()
                .map(order -> {
                    Optional<SecurityDefinition> data = securityRepository.findByTickerName(order.getSecurityDefinition().getTicker());
                    if (data.isPresent()) {
                        SecurityDefinition current = order.getSecurityDefinition();
                        current.setType(data.get().getType());
                        current.setMaturityDate(data.get().getMaturityDate());
                        current.setStrikePrice(data.get().getStrikePrice());
                        order.setSecurityDefinition(current);
                        String symbol = data.get().getType() == SecurityType.STOCK ? order.getSecurityDefinition().getTicker() : order.getSecurityDefinition().getTicker().split("-")[0];
                        order.updatePrice(symbol, Statics.INITIAL_PRICE.get(symbol));
                        return order;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println("###Initial position");
        initialized.forEach(System.out::println);
        System.out.println();
        eventPublisher.publishEvent(new PositionLoaded(this, initialized));
    }
}
