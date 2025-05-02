package br.com.pi.atostech.aplication.domain;

public record VideoDomain(
        byte[] buffer,
        Long start,
        Long end,
        Long fileLength,
        Long contentLength) {
}
