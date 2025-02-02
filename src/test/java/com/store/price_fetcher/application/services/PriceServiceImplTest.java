package com.store.price_fetcher.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.store.price_fetcher.application.dto.PriceDTO;
import com.store.price_fetcher.application.ports.outbounds.PriceRepository;
import com.store.price_fetcher.domain.entities.Price;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    private Price price;
    private PriceDTO priceDTO;

    @BeforeEach
    public void setUp() {
        price = new Price();
        price.setBrandId(1);
        price.setProductId(1);
        price.setStartDate(LocalDateTime.now());
        price.setEndDate(LocalDateTime.now().plusDays(1));
        price.setPriceList(1);
        price.setPriority(1);
        price.setPrice(new BigDecimal(100.0));
        price.setCurr("USD");

        priceDTO = new PriceDTO();
        priceDTO.setBrandId(1);
        priceDTO.setProductId(1);
        priceDTO.setStartDate(price.getStartDate());
        priceDTO.setEndDate(price.getEndDate());
        priceDTO.setPriceList(1);
        priceDTO.setPrice(new BigDecimal(100.0));
        priceDTO.setCurr("USD");
    }

    @Test
    public void testGetPrice() {
        when(priceRepository.findPrice(1, 1, price.getStartDate())).thenReturn(Optional.of(price));

        Optional<PriceDTO> result = priceService.getPrice(1, 1, price.getStartDate());

        assertTrue(result.isPresent());
        assertEquals(priceDTO, result.get());

        verify(priceRepository, times(1)).findPrice(1, 1, price.getStartDate());
    }

    @Test
    public void testGetPrice_NotFound() {
        when(priceRepository.findPrice(1, 1, price.getStartDate())).thenReturn(Optional.empty());

        Optional<PriceDTO> result = priceService.getPrice(1, 1, price.getStartDate());

        assertFalse(result.isPresent());

        verify(priceRepository, times(1)).findPrice(1, 1, price.getStartDate());
    }
}
