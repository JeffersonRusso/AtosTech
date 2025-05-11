package br.com.pi.atostech.adapters.in.api;

import br.com.pi.atostech.adapters.in.api.dto.mapper.VideoDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.response.DataResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.VideoInfoResponseDto;
import br.com.pi.atostech.adapters.out.storage.video.VideoAdapterOutInterface;
import br.com.pi.atostech.aplication.domain.VideoDomain;
import br.com.pi.atostech.aplication.domain.VideoInfoDomain;
import br.com.pi.atostech.aplication.video.VideoAplicationInterface;
import br.com.pi.atostech.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoAdapterIn {

    @Autowired
    private VideoAplicationInterface videoAplicationInterface;

    @GetMapping("/list_all_videos")
    public ResponseEntity<List<VideoInfoResponseDto>> listAllVideos() {
        log.info("Iniciando listagem dos videos");
        List<VideoInfoDomain> videoInfoDomains = videoAplicationInterface.listAllVideos();
        List<VideoInfoResponseDto> videoInfoResponseDto = VideoDtoMapper.toDto(videoInfoDomains);
        return ResponseEntity.ok(videoInfoResponseDto);
    }

    @PostMapping("/admin/upload/{course_id}")
    public ResponseEntity<DataResponseDto> uploadVideo(@RequestParam("file") MultipartFile file, @PathVariable("course_id") Integer courseId) {
        try {
            if (isNull(file) || isNull(courseId))
                return ResponseEntity.internalServerError().body(new DataResponseDto("Erro no upload: Algum parametro passado na requisicao está nulo."));

            boolean isUpload = videoAplicationInterface.uploadVideo(file, courseId);
            return isUpload
                    ? ResponseEntity.ok(new DataResponseDto("Upload realizado com sucesso"))
                    : ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel realizar o upload, entre em contato com um administrador"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new DataResponseDto("Erro no upload: " + e.getMessage()));
        }
    }

    @GetMapping("/stream/{courseId}/{videoPath}")
    public ResponseEntity<byte[]> geStreamVideo(
            @PathVariable Integer courseId,
            @PathVariable String videoPath,
            @RequestHeader(value = "Range", required = false) String rangeHeader) {
        VideoDomain video = videoAplicationInterface.getStreamVideo(courseId, videoPath, rangeHeader);
        HttpHeaders headers = setHeaders(videoPath, video);
        return new ResponseEntity<>(video.buffer(), headers, HttpStatus.PARTIAL_CONTENT);
    }

    private static HttpHeaders setHeaders(String videoName, VideoDomain video) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaTypeFactory.getMediaType(videoName)
                .orElse(MediaType.APPLICATION_OCTET_STREAM));
        headers.set("Content-Range", "bytes " + video.start() + "-" + video.end() + "/" + video.fileLength());
        headers.setContentLength(video.contentLength());
        headers.set("Accept-Ranges", "bytes");
        return headers;
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<DataResponseDto> deleteVideo(@PathVariable Integer id) {
        boolean isDeleted = videoAplicationInterface.deleteVideo(id);
        return isDeleted
                ? ResponseEntity.ok(new DataResponseDto("Delete realizado com sucesso"))
                : ResponseEntity.internalServerError().body(new DataResponseDto("Não foi possivel realizar o delete, entre em contato com o desenvolvedor"));
    }

}
