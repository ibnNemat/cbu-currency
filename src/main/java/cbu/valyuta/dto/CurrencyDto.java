package cbu.valyuta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
    public Integer id;
    @JsonProperty("Code")
    public String code;
    @JsonProperty("Ccy")
    public String ccy;
    @JsonProperty("CcyNm_RU")
    public String ccyNm_RU;
    @JsonProperty("CcyNm_UZ")
    public String ccyNm_UZ;
    @JsonProperty("CcyNm_UZC")
    public String ccyNm_UZC;
    @JsonProperty("CcyNm_EN")
    public String ccyNm_EN;
    @JsonProperty("Nominal")
    public String nominal;
    @JsonProperty("Rate")
    public String rate;
    @JsonProperty("Diff")
    public String diff;
    @JsonProperty("Date")
    public String date;
}
