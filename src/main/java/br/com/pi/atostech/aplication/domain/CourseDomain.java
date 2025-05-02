package br.com.pi.atostech.aplication.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
public class CourseDomain {

    private Integer id;
    private String title;
    private String description;
    private String path;
    private Boolean isActive;
    private String icon;
    private LocalDateTime createDate;
    private List<VideoInfoDomain> videos;

}
