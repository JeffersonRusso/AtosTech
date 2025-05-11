package br.com.pi.atostech.adapters.out.storage.course;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressEntity;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface CouseAdapterOutInterface {

    boolean createCourse(CourseDomain courseDomain);
    List<CourseDomain> listAllCourses();
    Optional<CourseEntity> getCourseByid(final Integer id);
    List<Optional<CourseProgressEntity>> findCourseProgressByCourseId(final Integer id);
    Path getCoursePath();
    boolean update(final CourseDomain courseDomain);
    void deleteCourse(CourseEntity id);
    void deleteDirectory(String path) throws IOException;
    void deleteCourseProgress(CourseEntity entity);
    boolean subscribe(CourseProgressEntity entity);
    List<CourseProgressEntity> getCourseProgressByUserEmail(final String emailUser);

}
