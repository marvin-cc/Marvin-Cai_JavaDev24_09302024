package com.crypto.dao;

import com.crypto.util.SecurityType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "security_definition")
public class SecurityDefinition {
    public SecurityDefinition() {

    }

    public SecurityDefinition(Long id, String ticker, BigDecimal price, SecurityType type, BigDecimal strikePrice, Date maturityDate) {
        this.id = id;
        this.ticker = ticker;
        this.price = price;
        this.type = type;
        this.strikePrice = strikePrice;
        this.maturityDate = maturityDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private SecurityType type;

    private BigDecimal strikePrice;

    @Temporal(TemporalType.DATE)
    private Date maturityDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SecurityType getType() {
        return type;
    }

    public void setType(SecurityType type) {
        this.type = type;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(BigDecimal strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }
}
