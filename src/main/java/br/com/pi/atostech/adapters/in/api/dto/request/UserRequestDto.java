package br.com.pi.atostech.adapters.in.api.dto.request;

import br.com.pi.atostech.adapters.out.storage.entities.user.RoleEntity;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class UserRequestDto {

    private String email;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String password;
    private RoleEntity role;

    public UserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
