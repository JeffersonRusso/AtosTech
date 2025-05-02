package br.com.pi.atostech.adapters.out.storage.entities.bible;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "testament")
public class Testament {

    @Id
    private Integer id;

    private String name;

}
