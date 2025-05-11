package br.com.pi.atostech.adapters.in.api.dto.mapper;

import br.com.pi.atostech.adapters.in.api.dto.request.UserRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.UserResponseDto;
import br.com.pi.atostech.aplication.domain.UserDomain;

import java.util.List;

public class UserDtoMapper {

    public static UserDomain toDomain(UserRequestDto userRequestDto) {
        return UserDomain.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .surname(userRequestDto.getSurname())
                .birthday(userRequestDto.getBirthday())
                .role(userRequestDto.getRole())
                .password(userRequestDto.getPassword())
                .build();
    }

    public static UserResponseDto toDto(UserDomain domain) {
        return UserResponseDto.builder()
                .email(domain.getEmail())
                .name(domain.getName())
                .surname(domain.getSurname())
                .birthday(domain.getBirthday())
                .role(domain.getRole())
                .build();
    }

    public static List<UserResponseDto> toDto(List<UserDomain> domain) {
        return domain.stream().map(UserDtoMapper::toDto).toList();
    }
}
