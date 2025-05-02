package br.com.pi.atostech.adapters.out.storage.entities.mapper;


import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;

import java.time.LocalDateTime;
import java.util.List;

public class CourseEntityMapper {

    private static final boolean isActive = true;

    public static CourseEntity toEntity(final String title, final String description, final String path) {
        return CourseEntity.builder()
                .title(title)
                .description(description)
                .path(path)
                .isActive(isActive)
                .createDate(LocalDateTime.now())
                .build();
    }

    public static List<CourseDomain> toDomain(List<CourseEntity> courseEntities) {
        return courseEntities.stream()
                .map(CourseEntityMapper::toDomain).toList();
    }

    public static CourseDomain toDomain(CourseEntity entity) {
        List<VideoInfoDomain> videoInfoDomainDomain = VideoEntityMapper.toVideoInfoDomainDomain(entity.getVideos());
        return CourseDomain.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .path(entity.getPath())
                .isActive(entity.isActive())
                .createDate(entity.getCreateDate())
                .icon(entity.getIcon())
                .videos(videoInfoDomainDomain)
                .build();
    }

    public static CourseEntity toEntity(CourseDomain courseDomain) {
        return CourseEntity.builder()
                .title(courseDomain.getTitle())
                .description(courseDomain.getDescription())
                .path(courseDomain.getPath())
                .isActive(courseDomain.getIsActive())
                .icon(courseDomain.getIcon())
                .build();
    }
}
