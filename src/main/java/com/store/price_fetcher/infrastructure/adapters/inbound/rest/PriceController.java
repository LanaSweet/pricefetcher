package com.store.price_fetcher.infrastructure.adapters.inbound.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.price_fetcher.application.dto.PriceDTO;
import com.store.price_fetcher.application.ports.inbound.PriceControllerPort;
import com.store.price_fetcher.application.ports.inbound.PriceService;
import com.store.price_fetcher.infrastructure.validators.DateTimeValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@Validated
public class PriceController implements PriceControllerPort{

    @Autowired
    private PriceService priceService;

    @Override
    @GetMapping("/prices")
    @Operation(summary = "Get price by productId, brandId, and dateTime")
    public ResponseEntity<PriceDTO> getPrice(
        @RequestParam @NotNull @Parameter(description = "Product ID", example = "1")  int productId,
        @RequestParam @NotNull @Parameter(description = "Brand ID", example = "1") int brandId,
        @RequestParam @DateTimeValidator @Parameter(description = "Date and time in format yyyy-MM-dd'T'HH:mm:ss", example = "2020-06-28T10:00:00") String dateTime) {
        
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