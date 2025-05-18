package br.com.pi.atostech.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class SecurityContextUtils {

    public static boolean isTypeUser(String role) {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getEmailUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Collection<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

