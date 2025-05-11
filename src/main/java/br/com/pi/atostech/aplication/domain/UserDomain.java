package br.com.pi.atostech.aplication.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class UserDomain {

    private final String email;
    private final String name;
    private final String surname;
    private final LocalDate birthday;
    private String role;
    private String password;

}
