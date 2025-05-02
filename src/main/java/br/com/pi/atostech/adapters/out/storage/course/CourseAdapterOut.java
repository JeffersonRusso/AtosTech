package br.com.pi.atostech.adapters.out.storage.course;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.CourseEntityMapper;
import br.com.pi.atostech.adapters.out.storage.repository.course.CourseRepository;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseAdapterOut implements CouseAdapterOutInterface {

    @Autowired
    private final CourseRepository courseRepository;

    private static final String COURSE_FOLDER = "course/";

    public boolean createCourse(CourseDomain courseDomain) {
        try {
            final Path videoDirectory = Paths.get(COURSE_FOLDER + courseDomain.getTitle());
            final CourseEntity courseEntity = CourseEntityMapper.toEntity(courseDomain);
            courseEntity.setCreateDate(LocalDateTime.now());
            courseEntity.setPath(courseDomain.getTitle());
            courseRepository.save(courseEntity);
            Files.createDirectories(videoDirectory);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar curso: " + e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("O curso com o nome " + courseDomain.getTitle() + " ja existe. Erro: " + e);
        }
    }

    public List<CourseDomain> listAllCourses() {
        List<CourseEntity> allCourses = courseRepository.findAll();
        return CourseEntityMapper.toDomain(allCourses);
    }

    public Optional<CourseEntity> getCourseByid(final Integer id) {
        return courseRepository.findById(id);
    }

    public Path getCoursePath() {
       return Paths.get(COURSE_FOLDER);
    }

    @Override
    public boolean update(CourseDomain courseDomain) {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(courseDomain.getId());
        CourseEntity courseEntity = courseEntityOptional.get();
        courseEntity.setActive(courseDomain.getIsActive());
        courseEntity.setDescription(courseDomain.getDescription());
        courseRepository.save(courseEntity);
        return true;
    }

    private Path createCourseDirectory() {
        try {
            Path videoDir = Paths.get(COURSE_FOLDER);
            if (Files.notExists(videoDir))
                return Files.createDirectories(videoDir);
            throw new RuntimeException("Curso já existe: ");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
