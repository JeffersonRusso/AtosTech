package br.com.pi.atostech.aplication.user;

import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.aplication.domain.UserDomain;
import jakarta.servlet.http.Cookie;

import java.util.List;

public interface UserApplicationInterface {

    boolean register(UserDomain userDomain);
    boolean updateRole(UserDomain userDomain);
    Cookie login(UserDomain userDomain);
    List<UserDomain> getAllUsers();
    CourseRequestDto getAllCourseProgress(String User);
}
