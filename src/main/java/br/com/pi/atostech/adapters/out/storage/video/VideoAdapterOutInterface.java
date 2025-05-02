package br.com.pi.atostech.adapters.out.storage.video;

import br.com.pi.atostech.adapters.out.storage.entities.video.VideoInfoEntity;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface VideoAdapterOutInterface {

    List<VideoInfoEntity> listAllVideos(final Path courseFolder) throws IOException;
    File getVideo(final String videoName);
    boolean uploadVideoDetails(final MultipartFile file, final Integer courseId, final Path targetPath);
    boolean uploadVideoFile(final MultipartFile file, final Path targetPath) throws IOException;
    Path getVideoPath(String path, String fileName, Path courseFolder);
    boolean delete(final Integer id);
}
