package br.com.pi.atostech.aplication.user;

import br.com.pi.atostech.aplication.domain.UserDomain;
import jakarta.servlet.http.Cookie;

public interface UserApplicationInterface {

    boolean register(UserDomain userDomain);
    Cookie login(UserDomain userDomain);
}
