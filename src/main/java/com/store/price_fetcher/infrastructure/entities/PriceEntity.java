package com.store.price_fetcher.infrastructure.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRICES")  
@Data
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long priceId;

    private int brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
    
    private int priceList;
    
    private int productId;

    private int priority;
    
    private BigDecimal price;
    
    private String curr;
}