package br.com.pi.atostech.adapters.out.storage.user;

import br.com.pi.atostech.adapters.out.storage.entities.user.UserEntity;
import br.com.pi.atostech.adapters.out.storage.entities.mapper.UserEntityMapper;
import br.com.pi.atostech.adapters.out.storage.repository.user.UserRepository;
import br.com.pi.atostech.aplication.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        if (userRepository.findByEmail(userDomain.getEmail()).isPresent()) {
            UserEntity userEntity = UserEntityMapper.toEntity(userDomain);
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
    public UserDomain getUser(String email) {
        return null;
    }
}
