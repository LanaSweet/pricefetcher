package com.store.price_fetcher.application.ports.inbound;

import com.store.price_fetcher.application.dto.PriceDTO;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<PriceDTO> getPrice(int productId, int brandId, LocalDateTime dateTime);
}