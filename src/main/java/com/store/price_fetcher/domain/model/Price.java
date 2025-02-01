package com.store.price_fetcher.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRICES")  
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("priceId")
    private Long priceId;

//    @JsonProperty("brandId")
    private int brandId;
    

//    @JsonProperty("startDate")
    private LocalDateTime startDate;

//    @JsonProperty("endDate")
    private LocalDateTime endDate;
    
//    @JsonProperty("priceList")
    private int priceList;
    

//    @JsonProperty("productId")
    private int productId;
    

//    @JsonProperty("priority")
    private int priority;
    
//    @JsonProperty("price")
    private BigDecimal price;
    
//    @JsonProperty("curr")
    private String curr;
}