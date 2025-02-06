package com.store.price_fetcher.infrastructure.adapters.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.store.price_fetcher.domain.services.PriceServiceImpl;

@Component
public class CacheEvictionTask {

    @Autowired
    private PriceServiceImpl priceService;

    @Scheduled(fixedRate = 3600000) // Clear cache every hour
    public void clearCachePeriodically() {
        priceService.clearCache();
    }
}

