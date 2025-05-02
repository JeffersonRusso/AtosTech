package br.com.pi.atostech.adapters.in.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseRequestDto {

    // TODO COLOCAR NOTNULL NOS CAMPOS
    private final String title;
    private final String description;
    private final Boolean isActive;
    private String icon;

}
