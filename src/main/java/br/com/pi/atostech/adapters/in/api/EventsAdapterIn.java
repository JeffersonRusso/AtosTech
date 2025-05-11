package br.com.pi.atostech.adapters.in.api;

import br.com.pi.atostech.adapters.in.api.dto.mapper.CourseDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.DataResponseDto;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@Slf4j
public class EventsAdapterIn {

//    @PostMapping("/create")
//    public ResponseEntity<DataResponseDto> createEvent(@RequestBody CourseRequestDto courseRequestDto)  {
//        log.info("Iniciando criacao do curso");
//        CourseDomain domain = CourseDtoMapper.toDomain(courseRequestDto);
//        return courseApplicationInterface.create(domain) ?
//                ResponseEntity.ok(new DataResponseDto("Curso criado com sucesso")) :
//                ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel criar o curso"));
//    }
}
