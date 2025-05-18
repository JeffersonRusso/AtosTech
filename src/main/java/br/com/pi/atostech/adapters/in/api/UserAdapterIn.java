package br.com.pi.atostech.adapters.in.api;

import br.com.pi.atostech.adapters.in.api.dto.mapper.UserDtoMapper;
import br.com.pi.atostech.adapters.in.api.dto.request.CourseRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.request.UserRequestDto;
import br.com.pi.atostech.adapters.in.api.dto.response.DataResponseDto;
import br.com.pi.atostech.adapters.in.api.dto.response.UserResponseDto;
import br.com.pi.atostech.adapters.out.storage.repository.course.progress.CourseProgressRepository;
import br.com.pi.atostech.aplication.domain.UserDomain;
import br.com.pi.atostech.aplication.user.UserApplication;
import br.com.pi.atostech.aplication.user.UserApplicationInterface;
import br.com.pi.atostech.utils.SecurityContextUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@Controller
@Slf4j
@RequestMapping(path = "api/user")
public class UserAdapterIn {

    @Autowired
    private final UserApplicationInterface userApplicationInterface;

    public UserAdapterIn(UserApplication userApplicationInterface) {
        this.userApplicationInterface = userApplicationInterface;
    }

    @PostMapping("/signup")
    public ResponseEntity<DataResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        UserDomain userDomain = UserDtoMapper.toDomain(userRequestDto);
        return userApplicationInterface.register(userDomain)
                // TODO Melhorar retorno
            ? ResponseEntity.ok(new DataResponseDto("Registro Realizado com sucesso"))
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponseDto("Não foi possivel realizar o registro"));
    }

    @PostMapping("/signin")
    public ResponseEntity<DataResponseDto> login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
            UserDomain userDomain = UserDtoMapper.toDomain(userRequestDto);
            Cookie cookie = userApplicationInterface.login(userDomain);
            if(isNull(cookie))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponseDto("Usuario ou senha incorreto"));
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body(new DataResponseDto("Login Realizado"));
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<UserResponseDto>> get() {
        List<UserDomain> allUsers = userApplicationInterface.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(UserDtoMapper.toDto(allUsers));
    }

    @PutMapping("/admin/role")
    public ResponseEntity<DataResponseDto> update(@RequestBody UserRequestDto userRequestDto) {
        UserDomain domain = UserDtoMapper.toDomain(userRequestDto);
        boolean isUpdated = userApplicationInterface.updateRole(domain);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponseDto(String.valueOf(isUpdated)));
    }

    @GetMapping("/user-info")
    public ResponseEntity<DataResponseDto> getUserInfo() {
        Collection<String> roles = SecurityContextUtils.getRoles();
        if(roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.OK).body(new DataResponseDto("ROLE_ADMIN"));
        } else if(roles.contains("ROLE_USER")) {
            return ResponseEntity.status(HttpStatus.OK).body(new DataResponseDto("ROLE_USER"));
        }
        throw new RuntimeException("Usuario não logado");
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return null;
    }
}