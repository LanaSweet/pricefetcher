package com.store.price_fetcher.infrastructure.adapters.outbound;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.price_fetcher.infrastructure.entities.PriceEntity;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId " +
           "AND :dateTime BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC limit 1")
    Optional<PriceEntity> findPrice(@Param("productId") int productId, 
                              @Param("brandId") int brandId, 
                              @Param("dateTime") LocalDateTime dateTime);
       
}