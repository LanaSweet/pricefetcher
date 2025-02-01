package com.store.price_fetcher.infrastructure.adapters.inbound.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.price_fetcher.application.dto.PriceDTO;
import com.store.price_fetcher.application.ports.inbound.PriceControllerPort;
import com.store.price_fetcher.application.ports.inbound.PriceService;

@RestController
public class PriceController implements PriceControllerPort{

    @Autowired
    private PriceService priceService;

    @Override
    @GetMapping("/prices")
    public ResponseEntity<PriceDTO> getPrice(
        @RequestParam int productId,
        @RequestParam int brandId,
        @RequestParam String dateTime) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTimeParsed = LocalDateTime.parse(dateTime, formatter);
        
        Optional<PriceDTO> price = priceService.getPrice(productId, brandId, dateTimeParsed);


        if (price.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return price.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}