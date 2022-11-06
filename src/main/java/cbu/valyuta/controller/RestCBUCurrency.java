package cbu.valyuta.controller;

import cbu.valyuta.dto.CurrencyDto;
import cbu.valyuta.dto.ResponseDto;
import cbu.valyuta.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valyuta")
@RequiredArgsConstructor
public class RestCBUCurrency {
    private final CurrencyService currencyService;

    @PostMapping
    public ResponseDto insertInto(){
        return currencyService.insertIntoCurrency();
    }

    @GetMapping("/by-param")
    public ResponseDto<Page<CurrencyDto>> getAllByParams(@RequestParam  Integer page, @RequestParam Integer size){
        return currencyService.getAllByParams(page, size);
    }

    @GetMapping
    public ResponseDto<List<CurrencyDto>> getAll(){
        return currencyService.getAll();
    }

    @GetMapping(value = "/by-code/{ccy}", produces = "application/json")
    public ResponseDto<CurrencyDto> getByCode(@PathVariable String ccy){
        return currencyService.getByCodeCurrency(ccy);
    }
}
