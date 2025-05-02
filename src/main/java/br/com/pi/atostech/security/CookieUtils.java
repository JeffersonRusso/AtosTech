package br.com.pi.atostech.security;

import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CookieUtils {

    public static Cookie generateCookieWithToken(String email, String role) throws JOSEException {
        String token = JwtUtils.generateToken(email, List.of(role));
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        return cookie;
    }
}
