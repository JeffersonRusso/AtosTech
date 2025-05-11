package br.com.pi.atostech.adapters.out.storage.entities.mapper;

import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import br.com.pi.atostech.aplication.domain.UserDomain;

import java.util.List;
import java.util.UUID;

public class UserEntityMapper {

    public static UserEntity toEntity(UserDomain userDomain) {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .name(userDomain.getName())
                .surname(userDomain.getSurname())
                .birthday(userDomain.getBirthday())
                .role(userDomain.getRole())
                .build();
    }

    public static UserDomain toDomain(UserEntity userEntity) {
        return UserDomain.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .surname(userEntity.getSurname())
                .birthday(userEntity.getBirthday())
                .role(userEntity.getRole())
                .password(userEntity.getPassword())
                .build();
    }

    public static List<UserDomain> toDomain(List<UserEntity> userEntities) {
        return userEntities.stream().map(UserEntityMapper::toDomain).toList();
    }

    public static UserEntity updateUser(UserDomain domain, UUID id) {
        return UserEntity.builder()
                .id(id)
                .email(domain.getEmail())
                .name(domain.getName())
                .surname(domain.getSurname())
                .birthday(domain.getBirthday())
                .role(domain.getRole())
                .password(domain.getPassword())
                .build();
    }
}
