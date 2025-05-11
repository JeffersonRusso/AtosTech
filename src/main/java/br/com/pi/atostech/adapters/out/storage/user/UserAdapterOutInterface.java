package br.com.pi.atostech.adapters.out.storage.user;

import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import br.com.pi.atostech.aplication.domain.UserDomain;

import java.util.List;
import java.util.UUID;

public interface UserAdapterOutInterface {

    UserDomain login(UserDomain userDomain);
    boolean create(UserDomain userDomain);
    boolean update(UserDomain userDomain);
    boolean delete(String email);
    UserEntity getUserByEmail(String email);
    List<UserEntity> getAllUsers();
    boolean subscribeCourse();
}
