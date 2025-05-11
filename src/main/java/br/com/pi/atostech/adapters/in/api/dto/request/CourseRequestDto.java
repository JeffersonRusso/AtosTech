package br.com.pi.atostech.adapters.in.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CourseRequestDto {

    // TODO COLOCAR NOTNULL NOS CAMPOS
    private final String title;
    private final String shortDescription;
    private final String description;
    private final LocalDate releaseDate;
    // TODO CRIAR UMA DESCRICAO NORMAL E UMA RESUMIDA
    private final Boolean isActive;
    private String icon;

}
