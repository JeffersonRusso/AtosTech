package br.com.pi.atostech.aplication.course;

import br.com.pi.atostech.aplication.domain.CourseDomain;

import java.util.List;

public interface CourseApplicationInterface {

    List<CourseDomain> listAllCourses();
    CourseDomain getCourseByid(final Integer id);
    List<CourseDomain> getCourseProgressByUserEmail(final String emailUser);
    boolean subscribe(final String email, final Integer idCourse);
    boolean create(final CourseDomain courseDomain);
    boolean update(final CourseDomain courseDomain);
    boolean delete(final Integer id);
}
