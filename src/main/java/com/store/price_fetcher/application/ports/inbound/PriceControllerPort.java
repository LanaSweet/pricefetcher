package com.store.price_fetcher.application.ports.inbound;

import org.springframework.http.ResponseEntity;

import com.store.price_fetcher.application.dto.PriceDTO;

public interface PriceControllerPort {
    ResponseEntity<PriceDTO> getPrice(int productId, int brandId, String dateTime);
}