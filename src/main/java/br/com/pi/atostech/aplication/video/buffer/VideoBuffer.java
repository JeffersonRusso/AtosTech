package br.com.pi.atostech.aplication.video.buffer;

import br.com.pi.atostech.aplication.domain.VideoDomain;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

@Component
public class VideoBuffer implements VideoBufferInterface {

    private static final long CHUNK_SIZE = 1024 * 1024;

    public VideoDomain getVideoBuffer(File video, String rangeHeader) {
        long fileLength = video.length();
        long start = 0;
        long end = fileLength - 1;

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            String[] ranges = rangeHeader.substring(6).split("-");
            start = Long.parseLong(ranges[0]);
            if (ranges.length > 1 && !ranges[1].isEmpty()) {
                end = Long.parseLong(ranges[1]);
            }
        }
        end = Math.min(end, start + CHUNK_SIZE - 1);
        long contentLength = end - start + 1;

        byte[] buffer = new byte[(int) contentLength];
        try (RandomAccessFile raf = new RandomAccessFile(video, "r")) {
            raf.seek(start);
            raf.readFully(buffer);
            return new VideoDomain(buffer, start, end, fileLength, contentLength);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
