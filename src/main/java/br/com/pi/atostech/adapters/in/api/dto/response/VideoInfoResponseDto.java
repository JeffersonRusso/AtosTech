package br.com.pi.atostech.adapters.in.api.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VideoInfoResponseDto {

    private final Integer id;
    private final String title;
    private final String path;

}
