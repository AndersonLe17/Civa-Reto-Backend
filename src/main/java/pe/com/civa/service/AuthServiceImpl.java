package pe.com.civa.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pe.com.civa.config.cookie.CookieUtil;
import pe.com.civa.config.security.JwtService;
import pe.com.civa.domain.dto.AuthResponse;
import pe.com.civa.domain.entities.Usuario;
import pe.com.civa.repository.UsuarioRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(Authentication authentication, HttpServletResponse response) {
        Usuario usuario = usuarioRepository.findByUsuNomIgnoreCase(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        String jwt = jwtService.generateToken(usuario);

        CookieUtil.create(response, "jwt", jwt, true, true, jwtService.getExpiredSeconds(jwt), "/");
        return AuthResponse.builder().jwt(jwt).build();
    }

    @Override
    public Optional<String> auth(String jwt) throws Exception {
        if (jwtService.verifyJWT(jwt)) {
            return Optional.of(jwt);
        }
        throw new Exception("Token no valido");
    }

    @Override
    public void logout(HttpServletResponse response) {
        CookieUtil.clear(response,"jwt", true, true, "/");
    }

}
