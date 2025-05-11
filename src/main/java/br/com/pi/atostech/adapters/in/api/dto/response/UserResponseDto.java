package br.com.pi.atostech.adapters.in.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserResponseDto {

    private String email;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String role;

}
