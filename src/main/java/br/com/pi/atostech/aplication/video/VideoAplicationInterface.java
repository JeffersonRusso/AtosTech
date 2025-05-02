package br.com.pi.atostech.aplication.video;

import br.com.pi.atostech.aplication.domain.VideoDomain;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoAplicationInterface {

    List<VideoInfoDomain> listAllVideos();
    boolean uploadVideo(MultipartFile file, Integer courseId);
    VideoDomain getStreamVideo(Integer couseId ,String videoName, String rangeHeader);
    boolean deleteVideo(Integer id);
}
