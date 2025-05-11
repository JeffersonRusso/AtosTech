package br.com.pi.atostech.adapters.out.storage.entities.course.progress;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import br.com.pi.atostech.adapters.out.storage.entities.video.progress.VideoProgressEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "TB_COURSE_PROGRESS")
@Entity
@Builder
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CourseProgressEntity {

    @EmbeddedId
    private CourseProgressId id;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
    private CourseEntity course;

    @OneToMany(mappedBy = "courseProgress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoProgressEntity> videoProgress;

}