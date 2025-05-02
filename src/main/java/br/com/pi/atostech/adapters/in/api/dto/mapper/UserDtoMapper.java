package br.com.pi.atostech.adapters.in.api.dto.mapper;

import br.com.pi.atostech.adapters.in.api.dto.request.UserRequestDto;
import br.com.pi.atostech.adapters.out.storage.entities.user.RoleEntity;
import br.com.pi.atostech.aplication.domain.UserDomain;

public class UserDtoMapper {

    public static UserDomain toDomain(UserRequestDto userRequestDto) {
        return new UserDomain(
                userRequestDto.getEmail(),
                userRequestDto.getName(),
                userRequestDto.getSurname(),
                userRequestDto.getBirthday(),
                RoleEntity.USER.name(),
                userRequestDto.getPassword());
    }
}
