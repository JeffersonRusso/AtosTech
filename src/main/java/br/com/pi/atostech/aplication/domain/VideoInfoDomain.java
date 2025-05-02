package br.com.pi.atostech.aplication.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class VideoInfoDomain {

    private final Integer id;
    private final String title;
    private final String path;

}
