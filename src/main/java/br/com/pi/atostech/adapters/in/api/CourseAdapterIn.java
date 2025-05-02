package br.com.pi.atostech.adapters.in.api;

import br.com.pi.atostech.adapters.in.api.dto.mapper.CourseDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.mapper.VideoDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.CourseResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.DataResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.VideoInfoResponseDto;
import br.com.pi.atostech.aplication.course.CourseApplicationInterface;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import br.com.pi.atostech.aplication.domain.VideoDomain;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseAdapterIn {

    @Autowired
    private final CourseApplicationInterface courseApplicationInterface;

    public CourseAdapterIn(CourseApplicationInterface courseApplicationInterface) {
        this.courseApplicationInterface = courseApplicationInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<DataResponseDto> createCourse(@RequestBody CourseRequestDto courseRequestDto)  {
        log.info("Iniciando criacao do curso");
        CourseDomain domain = CourseDtoMapper.toDomain(courseRequestDto);
        return courseApplicationInterface.create(domain) ?
        ResponseEntity.ok(new DataResponseDto("Curso criado com sucesso")) :
        ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel criar o curso"));
    }

    @GetMapping("/list_all_courses")
    public ResponseEntity<List<CourseResponseDto>> listAllCourses()  {
        log.info("Iniciando listagem dos cursos");

        List<CourseDomain> courseDomains = courseApplicationInterface.listAllCourses();
        List<CourseResponseDto> dto = CourseDtoMapper.toDto(courseDomains);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable("course_id") Integer courseId) {
        log.info("Iniciando get do curso com ID {}", courseId);
        CourseDomain courseDomain = courseApplicationInterface.getCourseByid(courseId);
        CourseResponseDto courseResponseDto = CourseDtoMapper.toDto(courseDomain);
        log.info("Curso retornado {}", courseDomain);
        return ResponseEntity.ok(courseResponseDto);
    }

    @PutMapping("/{course_id}")
    public ResponseEntity<DataResponseDto> putCourseById(@RequestBody CourseRequestDto courseRequestDto, @PathVariable("course_id") Integer courseId) {
        log.info("Iniciando put do curso com ID {}", courseId);
        CourseDomain domain = CourseDtoMapper.toDomainWithId(courseRequestDto, courseId);
        return courseApplicationInterface.update(domain)
                    ? ResponseEntity.ok(new DataResponseDto("update realizado com sucesso"))
                    : ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel realizar o update , entre em contato com um administrador"));
    }

}
