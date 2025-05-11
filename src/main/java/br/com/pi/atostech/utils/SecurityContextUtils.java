package br.com.pi.atostech.utils;

import org.springframework.security.core.context.SecurityContextHolder;

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
}

