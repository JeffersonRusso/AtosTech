package br.com.pi.atostech.adapters.in.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CourseResponseDto {

    private final Integer id;
    private final String title;
    private final String shortDescription;
    private final String description;
    private final String path;
    private final Boolean isActive;
    private final String icon;
    private final LocalDateTime createDate;
    private final LocalDate releaseDate;
    private final List<VideoInfoResponseDto> videos;
    // TODO FLAG PARA VER SE O VIDEO FOI ASSISTIDO PELO ALUNO E MARCAR NO FRONT QUE JÁ FOI ASSISTIDO
    private final boolean isWatched;
}
