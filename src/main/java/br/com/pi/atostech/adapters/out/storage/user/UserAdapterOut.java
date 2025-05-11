package br.com.pi.atostech.adapters.out.storage.user;

import br.com.pi.atostech.adapters.out.storage.entities.user.UserRoleEntity;
import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.UserEntityMapper;
import br.com.pi.atostech.adapters.out.storage.repository.user.UserRepository;
import br.com.pi.atostech.aplication.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Component
public class UserAdapterOut implements UserAdapterOutInterface{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserAdapterOut(
            final UserRepository userRepository,
            final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean create(UserDomain userDomain) {
        boolean hasUser = userRepository.findByEmail(userDomain.getEmail()).isPresent();
        if(hasUser)
            return false;
        UserEntity userEntity = UserEntityMapper.toEntity(userDomain);
        userEntity.setRole(UserRoleEntity.USER.name());
        userEntity.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public UserDomain login(UserDomain userDomain) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(userDomain.getEmail());
        return userEntity.map(UserEntityMapper::toDomain).orElse(null);
    }

    public boolean update(UserDomain userDomain) {
        Optional<UserEntity> entity = userRepository.findByEmail(userDomain.getEmail());
        if (entity.isPresent()) {
            UserEntity userEntity = UserEntityMapper.updateUser(userDomain, entity.get().getId());
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    public boolean delete(String email) {
        UUID id = userRepository.findByEmail(email).map(UserEntity::getId).orElse(null);
        if(nonNull(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserEntity getUserByEmail(final String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean subscribeCourse() {
        return false;
    }
}
