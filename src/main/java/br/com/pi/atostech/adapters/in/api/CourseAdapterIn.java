package br.com.pi.atostech.adapters.in.api;

import br.com.pi.atostech.adapters.in.api.dto.mapper.CourseDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.CourseResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.DataResponseDto;
import br.com.pi.atostech.aplication.course.CourseApplicationInterface;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import br.com.pi.atostech.utils.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseAdapterIn {

    @Autowired
    private final CourseApplicationInterface courseApplicationInterface;

    public CourseAdapterIn(CourseApplicationInterface courseApplicationInterface) {
        this.courseApplicationInterface = courseApplicationInterface;
    }

    @PostMapping("/admin/create")
    public ResponseEntity<DataResponseDto> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        try {
            log.info("Iniciando criacao do curso");
            CourseDomain domain = CourseDtoMapper.toDomain(courseRequestDto);
            return courseApplicationInterface.create(domain) ?
                    ResponseEntity.ok(new DataResponseDto("O curso \""+ courseRequestDto.getTitle() + "\" foi criado com sucesso")) :
                    ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel criar o curso"));
        } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body(new DataResponseDto("O curso com o nome \"" + courseRequestDto.getTitle() + "\" já existe."));
        }
    }

    @GetMapping("/list_all_courses")
    public ResponseEntity<List<CourseResponseDto>> listAllCourses() {
        log.info("Iniciando listagem dos cursos");

        List<CourseDomain> courseDomains = courseApplicationInterface.listAllCourses();
        List<CourseResponseDto> dto = CourseDtoMapper.toDto(courseDomains);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get/{course_id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable("course_id") Integer courseId) {
        log.info("Iniciando get do curso com ID {}", courseId);
        CourseDomain courseDomain = courseApplicationInterface.getCourseByid(courseId);
        CourseResponseDto courseResponseDto = CourseDtoMapper.toDto(courseDomain);
        log.info("Curso retornado {}", courseDomain);
        return ResponseEntity.ok(courseResponseDto);
    }

    @PutMapping("admin/put/{course_id}")
    public ResponseEntity<DataResponseDto> putCourseById(@RequestBody CourseRequestDto courseRequestDto, @PathVariable("course_id") Integer courseId) {
        log.info("Iniciando put do curso com ID {}", courseId);
        CourseDomain domain = CourseDtoMapper.toDomainWithId(courseRequestDto, courseId);
        return courseApplicationInterface.update(domain)
                ? ResponseEntity.ok(new DataResponseDto("update realizado com sucesso"))
                : ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel realizar o update , entre em contato com um administrador"));
    }

    @DeleteMapping("/admin/delete/{course_id}")
    public ResponseEntity<DataResponseDto> delete(@PathVariable("course_id") Integer id) {
        log.info("Iniciando delete do curso com ID {}", id);
        return courseApplicationInterface.delete(id)
                ? ResponseEntity.ok(new DataResponseDto("delete realizado com sucesso"))
                : ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel realizar o delete , entre em contato com um administrador"));
    }

    @GetMapping("/subscribe/{course_id}")
    public ResponseEntity<Boolean> subscribe(@PathVariable("course_id") Integer id) {
        final String userEmail = SecurityContextUtils.getEmailUser();

        courseApplicationInterface.subscribe(userEmail, id);

        return ResponseEntity.ok(true);
    }

    @GetMapping("/user/select")
    public ResponseEntity<List<CourseResponseDto>> listAllCoursesByUserEmail() {
        String emailUser = SecurityContextUtils.getEmailUser();

        List<CourseDomain> courseProgressByUserEmail = courseApplicationInterface.getCourseProgressByUserEmail(emailUser);
        return ResponseEntity.ok(CourseDtoMapper.toDto(courseProgressByUserEmail));
    }

}
