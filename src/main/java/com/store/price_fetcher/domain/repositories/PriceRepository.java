package com.store.price_fetcher.domain.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import com.store.price_fetcher.domain.model.Price;

public interface PriceRepository {
    Optional<Price> findPrice(int productId, int brandId, LocalDateTime dateTime);
    void save(Price price);
}
