package com.store.price_fetcher.application.ports.outbounds;

import java.time.LocalDateTime;
import java.util.Optional;

import com.store.price_fetcher.domain.entities.Price;

public interface PriceRepository {
    Optional<Price> findPrice(int productId, int brandId, LocalDateTime dateTime);
    void save(Price price);
}
