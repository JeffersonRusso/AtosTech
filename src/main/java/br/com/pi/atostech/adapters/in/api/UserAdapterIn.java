package br.com.pi.atostech.adapters.in.api;

import br.com.pi.atostech.adapters.in.api.dto.mapper.UserDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.request.UserRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.DataResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.TokenResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.UserResponseDto;
import br.com.pi.atostech.aplication.domain.UserDomain;
import br.com.pi.atostech.aplication.user.UserApplication;
import br.com.pi.atostech.security.JwtUtils;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@RestController
@Controller
@Slf4j
@RequestMapping(path = "/user")
public class UserAdapterIn {

    @Autowired
    private final UserApplication userApplication;

    public UserAdapterIn(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto) {
        UserDomain userDomain = UserDtoMapper.toDomain(userRequestDto);
        boolean isRegister = userApplication.register(userDomain);
        return ResponseEntity.ok("Registro Realizado com sucesso");
    }

    @GetMapping("/login")
    public ResponseEntity<DataResponseDto> login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
            UserDomain userDomain = UserDtoMapper.toDomain(userRequestDto);
            Cookie cookie = userApplication.login(userDomain);
            if(isNull(cookie))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponseDto("Usuario ou senha incorreto"));
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body(new DataResponseDto("Login Realizado"));
    }

    @GetMapping("/get_all")
    public ResponseEntity<UserResponseDto> get() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return null;
    }

    @PostMapping
    public ResponseEntity<?> update() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return null;
    }
}