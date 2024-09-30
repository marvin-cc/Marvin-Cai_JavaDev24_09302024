package com.crypto.data;

import com.crypto.dao.SecurityDefinition;
import com.crypto.util.OptionPriceCalculator;
import com.crypto.util.Statics;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import static com.crypto.calculator.PositionCalculator.calculateYearToMature;

public class Order {

    public Order(SecurityDefinition securityDefinition, Long quantity, BigDecimal price) {
        this.securityDefinition = securityDefinition;
        this.quantity = quantity;
        this.price = price;
    }

    private SecurityDefinition securityDefinition;

    private Long quantity;

    private BigDecimal price;

    public SecurityDefinition getSecurityDefinition() {
        return securityDefinition;
    }

    public void setSecurityDefinition(SecurityDefinition securityDefinition) {
        this.securityDefinition = securityDefinition;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void updatePrice(String ticker, Double price) {
        switch (getSecurityDefinition().getType()) {
            case STOCK:
                setPrice(BigDecimal.valueOf(price));
                break;
            case PUT:
                setPrice(BigDecimal.valueOf(OptionPriceCalculator.calculatePutPrice(price,
                        getSecurityDefinition().getStrikePrice().doubleValue(),
                        0.02, Statics.SIGMA.get(ticker),
                        calculateYearToMature(getSecurityDefinition().getMaturityDate(), Date.from(Instant.now())))));
                break;
            case CALL:
                setPrice(BigDecimal.valueOf(OptionPriceCalculator.calculateCallPrice(price,
                        getSecurityDefinition().getStrikePrice().doubleValue(),
                        0.02, Statics.SIGMA.get(ticker),
                        calculateYearToMature(getSecurityDefinition().getMaturityDate(), Date.from(Instant.now())))));
                break;
            default:
                System.err.println("Unrecognized security type: " + getSecurityDefinition().getType());
        }
    }

    @Override
    public String toString() {
        return String.format("%-30s %-20.8f %-20d %-20.8f", securityDefinition.getTicker(), getPrice(), getQuantity(), price.multiply(BigDecimal.valueOf(getQuantity())));
    }
}
