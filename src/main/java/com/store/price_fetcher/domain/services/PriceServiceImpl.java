package com.store.price_fetcher.domain.services;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.store.price_fetcher.application.mappers.PriceMapper;
import com.store.price_fetcher.application.ports.outbounds.PriceRepository;
import com.store.price_fetcher.domain.PriceDomainObject;
import com.store.price_fetcher.domain.exceptions.PriceNotFoundException;
import com.store.price_fetcher.infrastructure.entities.PriceEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;
    
    @Autowired 
    private PriceMapper priceMapper;

    @Override
    @Cacheable("prices")
    public Optional<PriceDomainObject> getPrice(int productId, int brandId, LocalDateTime dateTime) {
        try {
            log.info("Fetching price from database for productId: {} and brandId: {}", productId, brandId);
            Optional<PriceEntity> priceEntity = priceRepository.findPrice(productId, brandId, dateTime);
            if (priceEntity.isPresent()) {
                return Optional.of(priceMapper.toDomainObject(priceEntity.get()));
                } else {
                return Optional.empty();
            }
         } catch (PriceNotFoundException e) {
            log.warn("Entity not found: {}", e.getMessage());
            return Optional.empty();
        }    
    }
        
    @CacheEvict(value = "prices", allEntries = true)
    public void clearCache() {
        log.info("Cache cleared");
    }

}