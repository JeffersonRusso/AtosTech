package br.com.pi.atostech.adapters.out.storage.entities.mapper;

import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.video.VideoEntity;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;

import java.time.LocalDateTime;
import java.util.List;

public class VideoEntityMapper {

    public static List<VideoInfoDomain> toVideoInfoDomainDomain(List<VideoEntity> videoEntityList) {
        return videoEntityList.stream()
                .map(entity ->
                        new VideoInfoDomain(
                                entity.getId(),
                                entity.getTitle(),
                                entity.getFilePath()
                        )).toList();
    }

    public static VideoEntity toVideoEntity(String videoName, String path, Integer courseId) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(courseId);

        return new VideoEntity(
                videoName,
                path,
                LocalDateTime.now(),
                courseEntity);
    }
}
