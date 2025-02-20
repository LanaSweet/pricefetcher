package com.store.price_fetcher.application.ports.outbounds;

import java.time.LocalDateTime;
import java.util.Optional;

import com.store.price_fetcher.infrastructure.entities.PriceEntity;

public interface PriceRepository {
    Optional<PriceEntity> findPrice(int productId, int brandId, LocalDateTime dateTime);
    void save(PriceEntity price);
}
