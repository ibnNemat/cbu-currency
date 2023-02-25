package cbu.valyuta.service.impl;

import cbu.valyuta.dto.ResponseDto;
import cbu.valyuta.entity.Currency;
import cbu.valyuta.feign.CurrencyFeignClient;
import cbu.valyuta.response.standart.ResponseMessage;
import cbu.valyuta.service.mapper.CurrencyMapper;
import cbu.valyuta.dto.CurrencyDto;
import cbu.valyuta.repository.CurrencyRepository;
import cbu.valyuta.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static cbu.valyuta.response.standart.ResponseCode.*;
import static cbu.valyuta.response.standart.ResponseMessage.OK_MESSAGE;
import static cbu.valyuta.response.standart.ResponseMessage.SAVED;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyFeignClient currencyFeignClient;
    private final CurrencyRepository repository;
    private final CurrencyMapper mapper;
    @Override
    public ResponseDto insertIntoCurrency() {
        try {
            List<CurrencyDto> valyutaDtoList = currencyFeignClient.getAllCurrency();
            List<Currency> currencyList = valyutaDtoList.stream()
                    .map(mapper::toEntity)
                    .toList();

            repository.saveAll(currencyList);
            return ResponseDto.builder()
                    .code(OK)
                    .message(SAVED)
                    .success(true)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseDto.builder()
                    .code(SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<CurrencyDto>> getAllByParams(Integer page, Integer size) {
        if (page == null || size == null){
            return ResponseDto.<Page<CurrencyDto>>builder()
                    .code(NULL_VALUE)
                    .responseData(null)
                    .message("Page or size is null")
                    .build();
        }
        try{
            Pageable pageable = PageRequest.of(page, size);
            Page<Currency> currencyPage = repository.findAll(pageable);

            Page<CurrencyDto> currencyDtos = currencyPage.map(mapper::toDto);
            return ResponseDto.<Page<CurrencyDto>>builder()
                    .code(OK)
                    .success(true)
                    .message(OK_MESSAGE)
                    .responseData(currencyDtos)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseDto.<Page<CurrencyDto>>builder()
                    .code(SERVER_ERROR)
                    .responseData(null)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<CurrencyDto> getByCodeCurrency(String ccy) {
        try {
            Optional<Currency> optional = repository.findByCcy(ccy);
            if (optional.isEmpty()){
                return ResponseDto.<CurrencyDto>builder()
                        .code(NOT_FOUND)
                        .message(ResponseMessage.NOT_FOUND)
                        .responseData(null)
                        .build();
            }

            CurrencyDto currencyDto = mapper.toDto(optional.get());
            return ResponseDto.<CurrencyDto>builder()
                    .code(OK)
                    .message(OK_MESSAGE)
                    .success(true)
                    .responseData(currencyDto)
                    .build();
        }catch (Exception e){
                log.error(e.getMessage());
                return ResponseDto.<CurrencyDto>builder()
                        .code(SERVER_ERROR)
                        .message(e.getMessage())
                        .responseData(null)
                        .build();
            }
    }

    @Override
    public ResponseDto<List<CurrencyDto>> getAll() {
        try {
            List<Currency> currencyList = repository.findAll();

            List<CurrencyDto> currencyDtos = currencyList.stream()
                    .map(mapper::toDto)
                    .toList();
            return ResponseDto.<List<CurrencyDto>>builder()
                    .code(OK)
                    .success(true)
                    .message(OK_MESSAGE)
                    .responseData(currencyDtos)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseDto.<List<CurrencyDto>>builder()
                    .code(SERVER_ERROR)
                    .responseData(null)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<CurrencyDto> save(CurrencyDto currencyDto) {
        log.debug("Request to create currency with param: {}", currencyDto);
        Currency currency = mapper.toEntity(currencyDto);
        log.debug("Successfully saved currency: {}", currency);
        return new ResponseDto<>(0, "SAVED", true, mapper.toDto(repository.save(currency)));
    }

}
