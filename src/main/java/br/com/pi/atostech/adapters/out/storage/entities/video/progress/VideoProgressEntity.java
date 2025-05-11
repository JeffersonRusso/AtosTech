package br.com.pi.atostech.adapters.out.storage.entities.video.progress;

import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressEntity;
import br.com.pi.atostech.adapters.out.storage.entities.video.VideoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_VIDEO_PROGRESS")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoProgressEntity {

    @EmbeddedId
    private VideoProgressId id;

    @ManyToOne
    @MapsId("courseProgressId")
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    })
    private CourseProgressEntity courseProgress;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "id", referencedColumnName = "id")
    private VideoEntity video;


    @Column(name = "watched")
    private boolean watched;

    @Column(name = "watched_date")
    private LocalDateTime watchedDate;
}

