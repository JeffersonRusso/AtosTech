package br.com.pi.atostech.aplication.video.buffer;

import br.com.pi.atostech.aplication.domain.VideoDomain;

import java.io.File;

public interface VideoBufferInterface {

    VideoDomain getVideoBuffer(File video, String rangeHeader);

}
