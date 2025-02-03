
package com.store.price_fetcher.infrastructure.adapters.inbound.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.store.price_fetcher.application.dto.PriceDTO;
import com.store.price_fetcher.domain.services.PriceService;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private PriceDTO priceDTO;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @BeforeEach
    public void setUp() {
        LocalDateTime dateTime = LocalDateTime.now();
        priceDTO = new PriceDTO();
        priceDTO.setBrandId(1);
        priceDTO.setProductId(1);
        priceDTO.setStartDate(dateTime);
        priceDTO.setEndDate(dateTime.minusMonths(1));
        priceDTO.setPriceList(1);
        priceDTO.setPrice(new BigDecimal(100.0));
        priceDTO.setCurr("USD");
    }

    @Test
    public void testGetPrice() {
        LocalDateTime dateTimeParsed = LocalDateTime.parse("2025-01-14T10:00:00", formatter);

        when(priceService.getPrice(1, 1, dateTimeParsed)).thenReturn(Optional.of(priceDTO));

        ResponseEntity<PriceDTO> response = priceController.getPrice(1, 1, "2025-01-14T10:00:00");

        assertEquals(ResponseEntity.ok(priceDTO), response);
    }

    @Test
    public void testGetPrice_NotFound() {
        LocalDateTime dateTimeParsed = LocalDateTime.parse("2024-01-14T10:00:00", formatter);
        when(priceService.getPrice(1, 1, dateTimeParsed)).thenReturn(Optional.empty());

        ResponseEntity<PriceDTO> response = priceController.getPrice(1, 1, "2024-01-14T10:00:00");

        assertEquals(ResponseEntity.noContent().build(), response);
    }
}
