package br.com.pi.atostech.aplication.course;

import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.aplication.domain.CourseDomain;

import java.util.List;

public interface CourseApplicationInterface {

    boolean create(final CourseDomain courseDomain);
    boolean update(final CourseDomain courseDomain);
    List<CourseDomain> listAllCourses();
    CourseDomain getCourseByid(final Integer id);
}
