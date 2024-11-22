package com.totospz.eshop.controller;

import com.totospz.eshop.config.response.ResponseHttp;
import com.totospz.eshop.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(Authentication authentication, HttpServletResponse response) {
        return ResponseHttp.ok(authService.login(authentication, response));
    }

    @PreAuthorize("hasAuthority('SCOPE_REFRESH_TOKEN')")
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue("auth") String auth, HttpServletResponse response) {
        return ResponseHttp.ok(authService.refresh(auth, response));
    }

}



