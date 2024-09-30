package com.crypto.data;

import com.crypto.dao.SecurityDefinition;
import com.crypto.repositories.SecurityRepository;
import com.crypto.util.SecurityType;
import com.crypto.util.event.DBInitialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Component
public class DataInitialization implements CommandLineRunner {
    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void run(String... args) throws Exception {
        securityRepository.save(new SecurityDefinition(null, "AAPL", BigDecimal.valueOf(109d), SecurityType.STOCK, null, null));
        securityRepository.save(new SecurityDefinition(null, "AAPL-OCT-2026-120-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(120l), Date.from(Instant.parse("2020-10-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AAPL-SEP-2026-90-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(90l), Date.from(Instant.parse("2020-09-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AAPL-OCT-2025-100-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(100l), Date.from(Instant.parse("2020-12-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AAPL-NOV-2025-115-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(115l), Date.from(Instant.parse("2020-11-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AAPL-MAY-2027-130-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(130l), Date.from(Instant.parse("2020-05-01T00:00:00.00Z"))));

        securityRepository.save(new SecurityDefinition(null, "TSLA", BigDecimal.valueOf(200d), SecurityType.STOCK, null, null));
        securityRepository.save(new SecurityDefinition(null, "TSLA-OCT-2026-220-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(220l), Date.from(Instant.parse("2020-10-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "TSLA-SEP-2026-170-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(170l), Date.from(Instant.parse("2020-09-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "TSLA-OCT-2025-180-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(180l), Date.from(Instant.parse("2020-12-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "TSLA-NOV-2025-250-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(250l), Date.from(Instant.parse("2020-11-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "TSLA-MAY-2027-280-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(280l), Date.from(Instant.parse("2020-05-01T00:00:00.00Z"))));

        securityRepository.save(new SecurityDefinition(null, "AMZN", BigDecimal.valueOf(180d), SecurityType.STOCK, null, null));
        securityRepository.save(new SecurityDefinition(null, "AMZN-OCT-2026-200-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(200l), Date.from(Instant.parse("2020-10-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AMZN-SEP-2026-160-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(160l), Date.from(Instant.parse("2020-09-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AMZN-OCT-2025-150-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(150l), Date.from(Instant.parse("2020-12-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AMZN-NOV-2025-185-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(185l), Date.from(Instant.parse("2020-11-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "AMZN-MAY-2027-220-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(220l), Date.from(Instant.parse("2020-05-01T00:00:00.00Z"))));

        securityRepository.save(new SecurityDefinition(null, "MSFT", BigDecimal.valueOf(300d), SecurityType.STOCK, null, null));
        securityRepository.save(new SecurityDefinition(null, "MSFT-OCT-2026-350-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(350l), Date.from(Instant.parse("2020-10-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "MSFT-SEP-2026-250-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(250l), Date.from(Instant.parse("2020-09-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "MSFT-OCT-2025-265-P", BigDecimal.valueOf(0.0d), SecurityType.PUT, BigDecimal.valueOf(265l), Date.from(Instant.parse("2020-12-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "MSFT-NOV-2025-320-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(320l), Date.from(Instant.parse("2020-11-01T00:00:00.00Z"))));
        securityRepository.save(new SecurityDefinition(null, "MSFT-MAY-2027-360-C", BigDecimal.valueOf(0.0d), SecurityType.CALL, BigDecimal.valueOf(360l), Date.from(Instant.parse("2020-05-01T00:00:00.00Z"))));

        eventPublisher.publishEvent(new DBInitialized(this));
    }
}
