package com.store.price_fetcher.infrastructure.adapters.outbound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.store.price_fetcher.domain.exceptions.PriceNotFoundException;
import com.store.price_fetcher.infrastructure.entities.PriceEntity;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryImplTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @InjectMocks
    private PriceRepositoryImpl priceRepositoryImpl;

    private PriceEntity price;
    private LocalDateTime dateTime;

    @BeforeEach
    public void setUp() {
        dateTime = LocalDateTime.now();
        price = new PriceEntity();
        price.setBrandId(1);
        price.setProductId(1);
        price.setStartDate(dateTime.minusDays(10));
        price.setEndDate(dateTime.plusDays(10));
        price.setPriceList(1);
        price.setPriority(1);
        price.setPrice(new BigDecimal("100.0"));
        price.setCurr("USD");
    }

    @Test
    public void testFindPrice_Success() {
        when(jpaPriceRepository.findPrice(1, 1, dateTime)).thenReturn(Optional.of(price));

        Optional<PriceEntity> result = priceRepositoryImpl.findPrice(1, 1, dateTime);

        assertTrue(result.isPresent());
        assertEquals(price, result.get());
        
    }

    @Test
    public void testFindPrice_EntityNotFoundException() {
        when(jpaPriceRepository.findPrice(1, 1, dateTime)).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> {
            priceRepositoryImpl.findPrice(1, 1, dateTime);
        });
    }
}
