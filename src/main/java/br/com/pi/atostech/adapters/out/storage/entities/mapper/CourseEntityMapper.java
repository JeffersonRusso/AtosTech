package br.com.pi.atostech.adapters.out.storage.entities.mapper;


import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.course.progress.CourseProgressEntity;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import br.com.pi.atostech.aplication.domain.VideoDomain;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;

import java.util.List;

public class CourseEntityMapper {

    private static final boolean isActive = true;

    public static List<CourseDomain> toDomain(List<CourseEntity> courseEntities) {
        return courseEntities.stream()
                .map(CourseEntityMapper::toDomain).toList();
    }

    public static CourseDomain toDomain(CourseEntity entity) {
        List<VideoInfoDomain> videoInfoDomainDomain = VideoEntityMapper.toVideoInfoDomainDomain(entity.getVideos());
        return CourseDomain.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .shortDescription(entity.getShortDescription())
                .description(entity.getDescription())
                .path(entity.getPath())
                .isActive(entity.isActive())
                .createDate(entity.getCreateDate())
                .releaseDate(entity.getReleaseDate())
                .icon(entity.getIcon())
                .videos(videoInfoDomainDomain)
                .build();
    }

    public static CourseEntity toEntity(CourseDomain domain) {
        return CourseEntity.builder()
                .title(domain.getTitle())
                .shortDescription(domain.getShortDescription())
                .description(domain.getDescription())
                .path(domain.getPath())
                .isActive(domain.getIsActive())
                .releaseDate(domain.getReleaseDate())
                .icon(domain.getIcon())
                .build();
    }

    public static void updateEntity(CourseEntity entity, CourseDomain domain) {
        entity.setActive(domain.getIsActive());
        entity.setShortDescription(domain.getShortDescription());
        entity.setDescription(domain.getDescription());
        entity.setReleaseDate(domain.getReleaseDate());
    }

    public static CourseDomain toDomain(CourseProgressEntity entity) {
        List<VideoInfoDomain> videoInfoDomain = entity.getVideoProgress().stream().map(video ->
                new VideoInfoDomain(
                        video.getVideo().getId(),
                        video.getVideo().getTitle(),
                        video.getVideo().getFilePath()
                )).toList();

//                video.getVideo().getFilePath()).toList();
        Integer id = entity.getCourse().getId();

//        CourseDomain
        return null;
    }
}
