package br.com.pi.atostech.adapters.in.api.dto.mapper;

import br.com.pi.atostech.adapters.in.api.dto.response.VideoInfoResponseDto;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;

import java.util.List;

public class VideoDtoMapper {

    public static List<VideoInfoResponseDto> toDto(final List<VideoInfoDomain> videoInfoDomains) {
        return videoInfoDomains.stream().map(videoInfo ->
                new VideoInfoResponseDto(
                        videoInfo.getId(),
                        videoInfo.getTitle(),
                        videoInfo.getPath()
                    )).toList();
    }
}
