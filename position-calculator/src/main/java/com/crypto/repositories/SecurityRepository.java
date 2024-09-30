package com.crypto.repositories;

import com.crypto.dao.SecurityDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityDefinition, Long> {
    @Query(value = "SELECT * FROM security_definition WHERE ticker = ?", nativeQuery = true)
    Optional<SecurityDefinition> findByTickerName(String ticker);
}