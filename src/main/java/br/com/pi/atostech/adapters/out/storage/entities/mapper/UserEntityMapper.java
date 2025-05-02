package br.com.pi.atostech.adapters.out.storage.entities.mapper;

import br.com.pi.atostech.adapters.out.storage.entities.user.RoleEntity;
import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import br.com.pi.atostech.aplication.domain.UserDomain;

import java.util.UUID;

public class UserEntityMapper {

    public static UserEntity toEntity(UserDomain userDomain) {
        return new UserEntity(
                UUID.randomUUID(),
                userDomain.getEmail(),
                userDomain.getName(),
                userDomain.getSurname(),
                userDomain.getBirthday(),
                RoleEntity.USER.name());
    }

    public static UserDomain toDomain(UserEntity userEntity) {
        return new UserDomain(
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getBirthday(),
                userEntity.getRole(),
                userEntity.getPassword());
    }
}
