package pe.com.civa.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import pe.com.civa.domain.dto.AuthResponse;

import java.util.Optional;

public interface AuthService {

    AuthResponse login(Authentication authentication, HttpServletResponse response);

    Optional<String> auth(String jwt) throws Exception;

    void logout(HttpServletResponse response);
}
