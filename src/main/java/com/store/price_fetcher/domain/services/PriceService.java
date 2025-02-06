package com.store.price_fetcher.domain.services;

import java.time.LocalDateTime;
import java.util.Optional;

import com.store.price_fetcher.domain.PriceDomainObject;

public interface PriceService {
    Optional<PriceDomainObject> getPrice(int productId, int brandId, LocalDateTime dateTime);
}