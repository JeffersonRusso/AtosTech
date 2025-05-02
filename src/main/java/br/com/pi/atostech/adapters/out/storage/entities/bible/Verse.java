package br.com.pi.atostech.adapters.out.storage.entities.bible;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "verse")
public class Verse {

    @Id
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    private Integer chapter;

    private Integer verse;

    private String text;

}

