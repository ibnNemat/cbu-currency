package cbu.valyuta.service;

import cbu.valyuta.dto.ResponseDto;
import cbu.valyuta.dto.CurrencyDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CurrencyService {
    ResponseDto insertIntoCurrency();
    ResponseDto<Page<CurrencyDto>> getAllByParams(Integer page, Integer size);
    ResponseDto<CurrencyDto> getByCodeCurrency(String ccy);
    ResponseDto<List<CurrencyDto>> getAll();
}
