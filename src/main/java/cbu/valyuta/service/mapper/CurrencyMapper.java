package cbu.valyuta.service.mapper;

import cbu.valyuta.entity.Currency;
import cbu.valyuta.dto.CurrencyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    Currency toEntity(CurrencyDto currencyDto);
    CurrencyDto toDto(Currency currency);
}
