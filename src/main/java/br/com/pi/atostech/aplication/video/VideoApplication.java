package br.com.pi.atostech.aplication.video;

import br.com.pi.atostech.adapters.out.storage.course.CouseAdapterOutInterface;
import br.com.pi.atostech.adapters.out.storage.entities.course.CourseEntity;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.CourseEntityMapper;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.VideoInfoEntityMapper;
import br.com.pi.atostech.adapters.out.storage.entities.video.VideoEntity;
import br.com.pi.atostech.adapters.out.storage.entities.video.VideoInfoEntity;
import br.com.pi.atostech.adapters.out.storage.video.VideoAdapterOutInterface;
import br.com.pi.atostech.aplication.domain.CourseDomain;
import br.com.pi.atostech.aplication.domain.VideoDomain;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;
import br.com.pi.atostech.aplication.video.buffer.VideoBufferInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class VideoApplication implements VideoAplicationInterface {

    @Autowired
    private final VideoAdapterOutInterface videoAdapterOutInterface;

    @Autowired
    private final CouseAdapterOutInterface couseAdapterOutInterface;

    @Autowired
    private final VideoBufferInterface videoBuffer;

    @Override
    public List<VideoInfoDomain> listAllVideos() {
        Path coursePath = couseAdapterOutInterface.getCoursePath();
        try {
            List<VideoInfoEntity> videoInfoEntities = videoAdapterOutInterface.listAllVideos(coursePath);
            return VideoInfoEntityMapper.toDomain(videoInfoEntities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean uploadVideo(final MultipartFile video, final Integer courseId) {
        try {
            CourseDomain courseDomain = couseAdapterOutInterface.getCourseByid(courseId)
                    .map(CourseEntityMapper::toDomain)
                    .orElseThrow(() -> new RuntimeException("O curso não foi encontrado"));

            if(verifyDuplicate(courseDomain, video.getOriginalFilename()))
                throw new RuntimeException("Já existe um video com esse nome");

            final Path coursePath = couseAdapterOutInterface.getCoursePath();
            final Path videoPath = videoAdapterOutInterface.getVideoPath(courseDomain.getPath(), video.getOriginalFilename(), coursePath);
            videoAdapterOutInterface.uploadVideoDetails(video, courseId, videoPath);

            return videoAdapterOutInterface.uploadVideoFile(video, videoPath);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar video. Erro: " + e);
        }
    }

    private boolean verifyDuplicate(CourseDomain CourseDomain, String videoTitle) {
        return CourseDomain.getVideos().stream().anyMatch(video -> videoTitle.equals(video.getTitle()));
    }

    public VideoDomain getStreamVideo(Integer courseId, String videoName, String rangeHeader) {
        Optional<VideoEntity> selectedVideo = couseAdapterOutInterface.getCourseByid(courseId)
                .map(CourseEntity::getVideos)
                .orElse(Collections.emptyList())
                .stream()
                .filter(video -> videoName.equals(video.getTitle()))
                .findFirst();

        File video = videoAdapterOutInterface.getVideo(selectedVideo.get().getFilePath());

        if (!video.exists())
            throw new RuntimeException("Video não encontrado");
        return videoBuffer.getVideoBuffer(video, rangeHeader);
    }

    @Override
    public boolean deleteVideo(Integer id) {
        return videoAdapterOutInterface.delete(id);
    }
}