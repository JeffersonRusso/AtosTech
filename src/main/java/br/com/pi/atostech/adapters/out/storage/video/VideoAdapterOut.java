package br.com.pi.atostech.adapters.out.storage.video;

import br.com.pi.atostech.adapters.out.storage.entities.mapper.VideoEntityMapper;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.VideoInfoEntityMapper;
import br.com.pi.atostech.adapters.out.storage.entities.video.VideoEntity;
import br.com.pi.atostech.adapters.out.storage.entities.video.VideoInfoEntity;
import br.com.pi.atostech.adapters.out.storage.repository.video.VideoRepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class VideoAdapterOut implements VideoAdapterOutInterface {

    @Autowired
    private VideoRepository videoRepository;

    public boolean uploadVideoDetails(final MultipartFile file, Integer courseId, Path targetPath) {
        VideoEntity videoEntity = VideoEntityMapper.toVideoEntity(file.getOriginalFilename(), targetPath.toString(), courseId);
        videoRepository.save(videoEntity);
        return true;
    }

    @Override
    public File getVideo(String videoPath) {
        return new File(videoPath);
    }

    public boolean uploadVideoFile(final MultipartFile file, Path targetPath) throws IOException {
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return true;

    }

    public Path getVideoPath(String path, String fileName, Path courseFolder) {
        String fileNameCleaned = StringUtils.cleanPath(Objects.requireNonNull(fileName));
        return courseFolder.resolve(path + "/" + fileNameCleaned);
    }

    public List<VideoInfoEntity> listAllVideos(Path courseFolder) throws IOException {
        if (!Files.exists(courseFolder))
            return List.of();
        return VideoInfoEntityMapper.mapVideosInFolderToEntity(courseFolder);
    }

    public boolean delete(Integer id) {
        try {
            // TODO FAZER O DELETE NO DIRETORIO DO CURSO, E SÓ CONFIRMAR A TRANSACAO DEPOIS DISSO.
            VideoEntity video = videoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vídeo não encontrado"));

            videoRepository.delete(video);

            return new File(video.getFilePath()).delete();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar deletar video. Erro: ", e);
        }
    }

}
