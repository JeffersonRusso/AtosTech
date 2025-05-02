package br.com.pi.atostech.adapters.out.storage.entities.video;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "TB_VIDEO")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private final String title;
    private final String filePath;
    private final LocalDateTime uploadDate;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private final CourseEntity course;
}