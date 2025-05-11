package br.com.pi.atostech.aplication.course;

import br.com.pi.atostech.adapters.out.storage.course.CouseAdapterOutInterface;
import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressEntity;
import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressId;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.CourseEntityMapper;
import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import br.com.pi.atostech.adapters.out.storage.user.UserAdapterOutInterface;
import br.com.pi.atostech.adapters.out.storage.video.VideoAdapterOut;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseApplication implements CourseApplicationInterface {

    @Autowired
    private final CouseAdapterOutInterface courseAdapterOutInterface;

    @Autowired
    private final UserAdapterOutInterface userAdapterOutInterface;

    @Autowired
    private final VideoAdapterOut videoAdapterOut;


    @Transactional
    public boolean create(final CourseDomain courseDomain) {
        return courseAdapterOutInterface.createCourse(courseDomain);
    }

    @Override
    public boolean update(CourseDomain courseDomain) {
        return courseAdapterOutInterface.update(courseDomain);
    }

    @Transactional
    @Override
    public boolean delete(Integer id) {
        try {
            // TODO SIMULAR UM ERRO VIA THROW PARA VERIFICAR SE A TRANSACTIONAL ESTÁ FUNFANDO
            CourseEntity courseEntity = courseAdapterOutInterface.getCourseByid(id)
                    .orElseThrow(() -> new RuntimeException("Nao foi possivel encontrar o curso."));

            videoAdapterOut.deleteAllVideosByCourse(courseEntity);
            courseAdapterOutInterface.deleteCourseProgress(courseEntity);
            courseAdapterOutInterface.deleteCourse(courseEntity);
            courseAdapterOutInterface.deleteDirectory(courseEntity.getPath());
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CourseDomain> listAllCourses() {
        return courseAdapterOutInterface.listAllCourses();
    }

    public CourseDomain getCourseByid(final Integer id) {
        return courseAdapterOutInterface.getCourseByid(id)
                .map(CourseEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("O curso não foi encontrado"));
    }

    @Override
    public List<CourseDomain> getCourseProgressByUserEmail(String emailUser) {
        List<CourseProgressEntity> courseProgressByUserEmail = courseAdapterOutInterface.getCourseProgressByUserEmail(emailUser);
        List<Integer> idCourse = courseProgressByUserEmail.stream()
                .map(course -> course.getCourse().getId()).toList();

        List<Optional<CourseEntity>> list = idCourse.stream().map(courseAdapterOutInterface::getCourseByid).toList();

        return CourseEntityMapper.toDomain(list.stream().map(course -> course.get()).toList());
    }

    @Override
    public boolean subscribe(final String emailUser, final Integer courseId) {
        try {
            Optional<CourseEntity> course = courseAdapterOutInterface.getCourseByid(courseId);
            videoAdapterOut.getVideoByCourseId(course.get().getId());
            UserEntity userByEmail = userAdapterOutInterface.getUserByEmail(emailUser);

            CourseProgressEntity build = CourseProgressEntity
                    .builder()
                    .id(CourseProgressId
                            .builder()
                            .courseId(course.get().getId())
                            .userId(userByEmail.getId()).build())
                    .course(course.get())
                    .user(userByEmail)
                    .completionDate(null)
                    .build();

            courseAdapterOutInterface.subscribe(build);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel se inscrever no curso: " + e);
        }
    }

}
