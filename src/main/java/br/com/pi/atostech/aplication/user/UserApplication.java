package br.com.pi.atostech.aplication.user;

import br.com.pi.atostech.adapters.out.storage.user.UserAdapterOutInterface;
import br.com.pi.atostech.aplication.domain.UserDomain;
import br.com.pi.atostech.security.CookieUtils;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserApplication implements UserApplicationInterface {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserAdapterOutInterface userAdapterOutInterface;

    public UserApplication(
            PasswordEncoder passwordEncoder,
            UserAdapterOutInterface userAdapterOutInterface) {
        this.passwordEncoder = passwordEncoder;
        this.userAdapterOutInterface = userAdapterOutInterface;
    }

    public boolean register(UserDomain userDomain) {
        return userAdapterOutInterface.create(userDomain);
    }

    public Cookie login(UserDomain userDomain) {
        try {
            UserDomain authenticateUser = userAdapterOutInterface.login(userDomain);
            if (isNull(authenticateUser) || !passwordEncoder.matches(userDomain.getPassword(), authenticateUser.getPassword()))
                return null;
            return CookieUtils.generateCookieWithToken(authenticateUser.getEmail(), authenticateUser.getRole());
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel realizar o login. Fale com um administrador");
        }
    }

    public UserDomain getUser(String email) {
        return null;
    }

    public void update(UserDomain userDomain) {
        userAdapterOutInterface.update(userDomain);
    }

    public void delete(String email) {
        userAdapterOutInterface.delete(email);
    }
}
