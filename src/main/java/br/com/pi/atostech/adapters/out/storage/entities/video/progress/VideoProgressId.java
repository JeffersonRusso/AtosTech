package br.com.pi.atostech.adapters.out.storage.entities.video.progress;

import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressId;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class VideoProgressId implements Serializable {

    private CourseProgressId courseProgressId;

    private Integer id;
}