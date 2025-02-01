package com.store.price_fetcher.infrastructure.adapters.outbound;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.store.price_fetcher.application.ports.outbounds.PriceRepository;
import com.store.price_fetcher.domain.entities.Price;
import com.store.price_fetcher.domain.exceptions.EntityNotFoundException;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;


        @Override
        public Optional<Price> findPrice(int productId, int brandId, LocalDateTime dateTime) {
            return Optional.ofNullable(jpaPriceRepository.findPrice(productId, brandId, dateTime)
                    .orElseThrow(() -> new EntityNotFoundException("Price not found for product " + productId + " and brand " + brandId)));
        }
    
    @Override
    public void save(Price price) {
        jpaPriceRepository.save(price);
    }
}