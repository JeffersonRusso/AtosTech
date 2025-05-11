package br.com.pi.atostech.adapters.out.storage.repository.course.progress;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseProgressRepository extends JpaRepository<CourseProgressEntity, Integer> {

    List<Optional<CourseProgressEntity>> findByUserEmail(String email);
    List<Optional<CourseProgressEntity>> findByCourseId(Integer id);
    void deleteAllByCourse(CourseEntity entity);
}
