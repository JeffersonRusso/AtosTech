package br.com.pi.atostech.aplication.course;

import br.com.pi.atostech.adapters.out.storage.course.CouseAdapterOutInterface;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.CourseEntityMapper;
import br.com.pi.atostech.aplication.video.buffer.VideoBufferInterface;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import br.com.pi.atostech.aplication.domain.VideoDomain;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseApplication implements CourseApplicationInterface {

    @Autowired
    private final CouseAdapterOutInterface couseAdapterOutInterface;

    public boolean create(final CourseDomain courseDomain) {
        return couseAdapterOutInterface.createCourse(courseDomain);
    }

    @Override
    public boolean update(CourseDomain courseDomain) {
        return couseAdapterOutInterface.update(courseDomain);
    }

    public List<CourseDomain> listAllCourses() {
        return couseAdapterOutInterface.listAllCourses();
    }

    public CourseDomain getCourseByid(final Integer id) {
        return couseAdapterOutInterface.getCourseByid(id)
                .map(CourseEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("O curso não foi encontrado"));
    }

}
