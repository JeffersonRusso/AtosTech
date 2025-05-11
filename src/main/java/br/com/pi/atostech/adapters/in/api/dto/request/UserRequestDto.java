package br.com.pi.atostech.adapters.in.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserRequestDto {

    private String email;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String password;
    private String role;

}
