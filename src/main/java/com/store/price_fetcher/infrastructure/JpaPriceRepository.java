package com.store.price_fetcher.infrastructure;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.price_fetcher.domain.model.Price;

public interface JpaPriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId " +
           "AND :dateTime BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC limit 1")
    Optional<Price> findPrice(@Param("productId") int productId, 
                              @Param("brandId") int brandId, 
                              @Param("dateTime") LocalDateTime dateTime);
       
}