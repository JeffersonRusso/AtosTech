package br.com.pi.atostech.adapters.out.storage.entities.user;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Table(name = "TB_USER")
@Entity
public class UserEntity implements Serializable {

    @Id
    private UUID id;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String password;
    private String role;

    public UserEntity() {}

    public UserEntity(UUID id, String email, String name, String surname, LocalDate birthday, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.role = role;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

}