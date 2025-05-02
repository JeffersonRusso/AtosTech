package br.com.pi.atostech.adapters.out.storage.repository.course;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}
