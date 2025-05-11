package br.com.pi.atostech.aplication.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class CourseDomain {

    private Integer id;
    private String title;
    private String shortDescription;
    private String description;
    private String path;
    private Boolean isActive;
    private String icon;
    private LocalDateTime createDate;
    private LocalDate releaseDate;
    private List<VideoInfoDomain> videos;

}
