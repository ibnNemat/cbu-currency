package cbu.valyuta.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.View;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "id", allowGetters = true)
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
    public Double rate;
    @JsonProperty("Diff")
    public String diff;
    @JsonProperty("Date")
    public String date;
}
