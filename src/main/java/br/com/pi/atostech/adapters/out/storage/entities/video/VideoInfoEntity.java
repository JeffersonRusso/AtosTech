package br.com.pi.atostech.adapters.out.storage.entities.video;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VideoInfoEntity {

    private final String title;
    private final String path;

}