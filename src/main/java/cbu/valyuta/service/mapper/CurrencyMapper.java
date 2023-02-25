package cbu.valyuta.service.mapper;
import cbu.valyuta.entity.Currency;
import cbu.valyuta.dto.CurrencyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    @Mapping(target = "date",source = "date", dateFormat = "dd.MM.yyyy")  // target -> entity, source -> dto
    Currency toEntity(CurrencyDto currencyDto);
    @Mapping(target = "date",source = "date", dateFormat = "dd.MM.yyyy")
    CurrencyDto toDto(Currency currency);
}