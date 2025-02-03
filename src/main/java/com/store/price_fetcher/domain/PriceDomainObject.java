package com.store.price_fetcher.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceDomainObject {


    private int productId;
    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String priceList;
    private BigDecimal price;
    private String curr;

}