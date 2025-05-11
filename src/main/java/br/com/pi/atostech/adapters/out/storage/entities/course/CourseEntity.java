package br.com.pi.atostech.adapters.out.storage.entities.course;

import br.com.pi.atostech.adapters.out.storage.entities.video.VideoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "TB_COURSE")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String title;
    private String shortDescription;
    //      ALTER TABLE TB_COURSE MODIFY COLUMN description VARCHAR(2000);
    @Column(length = 2000) // ou outro valor adequado
    private String description;
    private String path;
    private String icon;

    // ALTER TABLE tb_course MODIFY COLUMN is_active TINYINT(1) NOT NULL DEFAULT 1;
    private boolean isActive;
    private LocalDateTime createDate;
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoEntity> videos;

}