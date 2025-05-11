package br.com.pi.atostech.adapters.in.api.dto.mapper;

import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.CourseResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.VideoInfoResponseDto;
import br.com.pi.atostech.aplication.domain.CourseDomain;

import java.util.List;

public class CourseDtoMapper {

    public static List<CourseResponseDto> toDto(List<CourseDomain> courseDomains) {
        return courseDomains.stream()
                .map(CourseDtoMapper::toDto).toList();
    }

    public static CourseDomain toDomainWithId(CourseRequestDto dto, Integer id) {
        CourseDomain domain = toDomain(dto);
        domain.setId(id);
        return domain;
    }

    public static CourseDomain toDomain(CourseRequestDto dto) {
        return CourseDomain.builder()
                .title(dto.getTitle())
                .shortDescription(dto.getShortDescription())
                .description(dto.getDescription())
                .releaseDate(dto.getReleaseDate())
                .isActive(dto.getIsActive())
                .icon(dto.getIcon())
                .build();
    }

    public static CourseResponseDto toDto(CourseDomain courseDomain) {
        List<VideoInfoResponseDto> videoDto = VideoDtoMapper.toDto(courseDomain.getVideos());
        return CourseResponseDto.builder()
                .id(courseDomain.getId())
                .title(courseDomain.getTitle())
                .shortDescription(courseDomain.getShortDescription())
                .description(courseDomain.getDescription())
                .path(courseDomain.getPath())
                .isActive(courseDomain.getIsActive())
                .createDate(courseDomain.getCreateDate())
                .releaseDate(courseDomain.getReleaseDate())
                .icon(courseDomain.getIcon())
                .videos(videoDto)
                .build();
    }
}
