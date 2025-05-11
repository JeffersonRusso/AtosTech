package br.com.pi.atostech.adapters.out.storage.repository.video;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.video.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {

    int deleteAllByCourse(CourseEntity course);
    List<VideoEntity> findByCourseId(Integer courseId);

}
