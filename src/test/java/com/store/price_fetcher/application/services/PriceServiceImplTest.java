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
import com.store.price_fetcher.application.mappers.PriceMapper;
import com.store.price_fetcher.application.ports.outbounds.PriceRepository;
import com.store.price_fetcher.domain.services.PriceServiceImpl;
import com.store.price_fetcher.infrastructure.entities.PriceEntity;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    private PriceEntity priceEntity;
    private PriceDTO priceDTO;
    private LocalDateTime dateTime;

    @BeforeEach
    public void setUp() {
        dateTime = LocalDateTime.now();
        priceEntity = new PriceEntity();
        priceEntity.setBrandId(1);
        priceEntity.setProductId(1);
        priceEntity.setStartDate(dateTime.minusDays(1));
        priceEntity.setEndDate(dateTime.plusDays(1));
        priceEntity.setPriceList(1);
        priceEntity.setPriority(1);
        priceEntity.setPrice(new BigDecimal(100.0));
        priceEntity.setCurr("USD");

        priceDTO = new PriceDTO();
        priceDTO.setBrandId(1);
        priceDTO.setProductId(1);
        priceDTO.setStartDate(priceEntity.getStartDate());
        priceDTO.setPrice(new BigDecimal(100.0));
        priceDTO.setCurr("USD");
    }

    @Test
    public void testGetPrice() {
        when(priceRepository.findPrice(1, 1, dateTime)).thenReturn(Optional.of(priceEntity));
        when(priceMapper.toDto(priceMapper.toDomainObject(priceEntity))).thenReturn(priceDTO);

        Optional<PriceDTO> result = priceService.getPrice(1, 1, dateTime);

        assertTrue(result.isPresent());
        assertEquals(priceDTO, result.get());

        verify(priceRepository, times(1)).findPrice(1, 1, dateTime);
    }

    @Test
    public void testGetPrice_NotFound() {
        when(priceRepository.findPrice(1, 1, dateTime)).thenReturn(Optional.empty());

        Optional<PriceDTO> result = priceService.getPrice(1, 1, dateTime);

        assertFalse(result.isPresent());

        verify(priceRepository, times(1)).findPrice(1, 1, dateTime);
    }
}