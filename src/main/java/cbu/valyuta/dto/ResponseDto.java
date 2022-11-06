package cbu.valyuta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<R> {
    private Integer code;
    private String message;
    private Boolean success;
    private R responseData;
}
