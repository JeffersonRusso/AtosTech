package br.com.pi.atostech.adapters.out.storage.entities.bible;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "metadata")
public class Metadata {

    @Id
    @Column(name = "key_metadata")
    private String keyMetadata;

    @Column(name = "value")
    private String value;

}
