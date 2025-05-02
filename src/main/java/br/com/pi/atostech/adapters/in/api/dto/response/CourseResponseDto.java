package br.com.pi.atostech.adapters.in.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CourseResponseDto {

    private final Integer id;
    private final String title;
    private final String description;
    private final String path;
    private final Boolean isActive;
    private final String icon;
    private final LocalDateTime createDate;
    private final List<VideoInfoResponseDto> videos;

}
