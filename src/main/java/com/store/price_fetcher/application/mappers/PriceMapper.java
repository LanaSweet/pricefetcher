package com.store.price_fetcher.application.mappers;

import org.mapstruct.Mapper;

import com.store.price_fetcher.application.dto.PriceDTO;
import com.store.price_fetcher.domain.PriceDomainObject;
import com.store.price_fetcher.infrastructure.entities.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDTO toDto(PriceDomainObject domainObject);
    PriceDomainObject toDomainObject(PriceEntity entity);
    PriceEntity toEntity(PriceDomainObject domainObject);
}