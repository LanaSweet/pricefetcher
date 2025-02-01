package com.store.price_fetcher.application;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.price_fetcher.api.PriceDTO;
import com.store.price_fetcher.domain.model.Price;
import com.store.price_fetcher.domain.repositories.PriceRepository;


@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<PriceDTO> getPrice(int productId, int brandId, LocalDateTime dateTime) {
        Optional<Price> price = priceRepository.findPrice(productId, brandId, dateTime);
        return price.map(this::convertToDTO);
    }

    private PriceDTO convertToDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setBrandId(price.getBrandId());
        priceDTO.setStartDate(price.getStartDate());
        priceDTO.setEndDate(price.getEndDate());
        priceDTO.setPriceList(price.getPriceList());
        priceDTO.setProductId(price.getProductId());
        priceDTO.setPriority(price.getPriority());
        priceDTO.setPrice(price.getPrice());
        priceDTO.setCurr(price.getCurr());
        return priceDTO;
    }
}