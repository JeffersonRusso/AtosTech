package br.com.pi.atostech.adapters.in.api.dto.response;

import lombok.Data;

@Data
public class DataResponseDto {

    private String message;

    public DataResponseDto(final String messsage) {
        this.message = messsage;
    }
}
