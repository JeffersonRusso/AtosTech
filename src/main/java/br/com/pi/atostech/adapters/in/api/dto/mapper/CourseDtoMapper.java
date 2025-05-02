package br.com.pi.atostech.adapters.in.api.dto.mapper;

import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.CourseResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.VideoInfoResponseDto;
import br.com.pi.atostech.aplication.domain.CourseDomain;

import java.util.List;

public class CourseDtoMapper {

    public static List<CourseResponseDto> toDto(List<CourseDomain> courseDomains) {
        return courseDomains.stream()
                .map(domain -> {
                    List<VideoInfoResponseDto> videoDto = VideoDtoMapper.toDto(domain.getVideos());
                    return CourseResponseDto.builder()
                            .id(domain.getId())
                            .title(domain.getTitle())
                            .description(domain.getDescription())
                            .path(domain.getPath())
                            .isActive(domain.getIsActive())
                            .createDate(domain.getCreateDate())
                            .icon(domain.getIcon())
                            .videos(videoDto)
                            .build();
                }).toList();
    }

    public static CourseDomain toDomainWithId(CourseRequestDto dto, Integer id) {
        return CourseDomain.builder()
                .id(id)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .isActive(dto.getIsActive())
                .icon(dto.getIcon())
                .build();
    }

    public static CourseDomain toDomain(CourseRequestDto dto) {
        return CourseDomain.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .isActive(dto.getIsActive())
                .icon(dto.getIcon())
                .build();
    }

    public static CourseResponseDto toDto(CourseDomain courseDomain) {
        List<VideoInfoResponseDto> videoDto = VideoDtoMapper.toDto(courseDomain.getVideos());
        return CourseResponseDto.builder()
                .id(courseDomain.getId())
                .title(courseDomain.getTitle())
                .description(courseDomain.getDescription())
                .path(courseDomain.getPath())
                .isActive(courseDomain.getIsActive())
                .createDate(courseDomain.getCreateDate())
                .icon(courseDomain.getIcon())
                .videos(videoDto)
                .build();
    }
}
