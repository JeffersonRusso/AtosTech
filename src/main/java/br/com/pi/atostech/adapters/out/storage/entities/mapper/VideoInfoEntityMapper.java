package br.com.pi.atostech.adapters.out.storage.entities.mapper;

import br.com.pi.atostech.adapters.out.storage.entities.video.VideoInfoEntity;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class VideoInfoEntityMapper {

    public static List<VideoInfoDomain> toDomain(List<VideoInfoEntity> videoInfoEntities) {
        return videoInfoEntities.stream()
                .map(entity ->
                        new VideoInfoDomain(
                                null,
                                entity.getTitle(),
                                entity.getPath()
                        )).toList();
    }

    public static List<VideoInfoEntity> mapVideosInFolderToEntity(Path courseFolder) throws IOException {
        List<VideoInfoEntity> videoInfoEntities = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(courseFolder)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".mp4"))
                    .forEach(path -> {
                        String name = courseFolder.relativize(path).toString().replace("\\", "/");
                        String videoPath = "\\" + courseFolder + name;
                        videoInfoEntities.add(new VideoInfoEntity(name, videoPath));
                    });
        }
        return videoInfoEntities;
    }
}
