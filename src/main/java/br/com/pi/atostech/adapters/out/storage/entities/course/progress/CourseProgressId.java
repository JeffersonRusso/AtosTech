package br.com.pi.atostech.adapters.out.storage.entities.course.progress;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CourseProgressId implements Serializable {

    @Column(name = "user_id")
    private UUID userId;

    //TODO CRIAR INDEX
    @Column(name = "course_id")
    private Integer courseId;
}
