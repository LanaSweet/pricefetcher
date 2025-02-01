package com.store.price_fetcher.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.price_fetcher.application.PriceService;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public ResponseEntity<PriceDTO> getPrice(
        @RequestParam int productId,
        @RequestParam int brandId,
        @RequestParam String dateTime) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTimeParsed = LocalDateTime.parse(dateTime, formatter);
        
        Optional<PriceDTO> price = priceService.getPrice(productId, brandId, dateTimeParsed);
        price.ifPresent(p -> System.out.println(p.toString()));
        
        return price.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
}