package pe.com.civa.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.civa.config.response.ResponseHttp;
import pe.com.civa.service.AuthService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(Authentication authentication, HttpServletResponse response) {
        return ResponseHttp.ok(authService.login(authentication, response));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@CookieValue("jwt") String jwt) throws Exception {
        return ResponseHttp.ok(authService.auth(jwt));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        authService.logout(response);
        return ResponseHttp.ok(null);
    }

}



