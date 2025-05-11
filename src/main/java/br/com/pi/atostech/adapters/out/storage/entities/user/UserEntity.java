package br.com.pi.atostech.adapters.out.storage.entities.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "TB_USER")
@NoArgsConstructor (force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

}