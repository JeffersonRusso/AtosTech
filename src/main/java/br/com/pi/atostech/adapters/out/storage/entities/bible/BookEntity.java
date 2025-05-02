package br.com.pi.atostech.adapters.out.storage.entities.bible;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book")
public class BookEntity {

    @Id
    private Integer id;

    @Column(name = "book_reference_id")
    private Integer bookReferenceId;

    @Column(name = "testament_reference_id")
    private Integer testamentReferenceId;

    private String name;

}
