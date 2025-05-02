package br.com.pi.atostech.adapters.in.api.dto.response;

public class VideoResponseDto {

    private final Integer id;
    private final String name;
    private final String path;

    public VideoResponseDto(Integer id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
}
