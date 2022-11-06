package cbu.valyuta.feign;

import cbu.valyuta.dto.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://cbu.uz/uz/arkhiv-kursov-valyut/json", value = "currency-feign")
public interface CurrencyFeignClient {
    @GetMapping
    List<CurrencyDto> getAllCurrency();
}
