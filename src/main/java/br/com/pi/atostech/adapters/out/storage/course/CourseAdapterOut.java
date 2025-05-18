package br.com.pi.atostech.adapters.out.storage.course;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressEntity;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.CourseEntityMapper;
import br.com.pi.atostech.adapters.out.storage.repository.course.CourseRepository;
import br.com.pi.atostech.adapters.out.storage.repository.course.progress.CourseProgressRepository;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import br.com.pi.atostech.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private final CourseProgressRepository courseProgressRepository;

    @Value("${spring.local.save.course}")
    private String COURSE_FOLDER;

    public boolean createCourse(CourseDomain courseDomain) {
        try {
            final Path videoDirectory = Paths.get(COURSE_FOLDER + courseDomain.getTitle());
            final CourseEntity courseProgressEntity = CourseEntityMapper.toEntity(courseDomain);
            courseProgressEntity.setCreateDate(LocalDateTime.now());
            courseProgressEntity.setPath(courseDomain.getTitle());
            courseRepository.save(courseProgressEntity);
            Files.createDirectories(videoDirectory);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar curso: " + e);
        }
    }

    public List<CourseDomain> listAllCourses() {
        List<CourseEntity> allCourses = courseRepository.findAll();
        return CourseEntityMapper.toDomain(allCourses);
    }

    public Optional<CourseEntity> getCourseByid(final Integer id) {
        return courseRepository.findById(id);
    }

    public List<Optional<CourseProgressEntity>> findCourseProgressByCourseId(final Integer id) {
        return courseProgressRepository.findByCourseId(id);
    }

    public Path getCoursePath() {
        return Paths.get(COURSE_FOLDER);
    }

    @Override
    public boolean update(CourseDomain domain) {
        CourseEntity entity = courseRepository.findById(domain.getId())
                .orElseThrow(() -> new RuntimeException("Nao foi possivel encontrar o curso."));

        CourseEntityMapper.updateEntity(entity, domain);

        courseRepository.save(entity);
        return true;
    }

    public void deleteCourse(CourseEntity courseEntity) {
        courseRepository.delete(courseEntity);
    }

    public void deleteDirectory(String path) throws IOException {
        FileUtils.deleteDirectoryRecursively(Path.of(COURSE_FOLDER + path));
    }

    public void deleteCourseProgress(CourseEntity entity) {
        courseProgressRepository.deleteAllByCourse(entity);
    }


    public boolean subscribe(CourseProgressEntity entity) {
        courseProgressRepository.save(entity);
        return true;
    }

    @Override
    public List<CourseProgressEntity> getCourseProgressByUserEmail(String emailUser) {
        List<Optional<CourseProgressEntity>> courseProgress = courseProgressRepository.findByUserEmail(emailUser);
        return courseProgress.stream().map(Optional::get).toList();
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
