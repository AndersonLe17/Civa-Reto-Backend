package pe.com.civa.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.civa.domain.entities.Usuario;
import pe.com.civa.repository.UsuarioRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;


@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${api.security.issue}")
    private String jwtIssuer;

    @Value("${api.security.secret}")
    private String apiSecret;

    @Value("${api.security.expiration}")
    private Integer apiExpiration;

    private final UsuarioRepository usuarioRepository;


    public String generateToken(Usuario usuario) {
        try {
            return JWT.create()
                    .withIssuer(jwtIssuer)
                    .withSubject(usuario.getUsuNom())
                    .withClaim("name", usuario.getUsuNomCom())
                    .withClaim("scope", usuario.getUsuPer().toString())
                    .withExpiresAt(generateExpirationToken())
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new IllegalArgumentException("Error al generar el token");
        }
    }

    public CustomUserDetails userDetails(String username) {
        return usuarioRepository.findByUsuNomIgnoreCase(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public Boolean verifyJWT(String jwt) {
        try {
            JWT.require(getAlgorithm())
                    .withIssuer(jwtIssuer)
                    .build()
                    .verify(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        DecodedJWT verifier = JWT.require(getAlgorithm())
                .withIssuer(jwtIssuer)
                .build()
                .verify(token);

        return verifier.getSubject();
    }

    public Long getExpiredTime(String token) {
        DecodedJWT verifier = JWT.require(getAlgorithm())
                .withIssuer(jwtIssuer)
                .build()
                .verify(token);

        return verifier.getExpiresAtAsInstant().getEpochSecond();
    }

    public Integer getExpiredSeconds(String token) {
        Long now = Instant.now().getEpochSecond();
        Long expired = this.getExpiredTime(token);
        return (int) (expired - now);
    }

    public Instant generateExpirationToken() {
        return LocalDateTime.now().plusMinutes(apiExpiration).toInstant(ZoneOffset.of("-05:00"));
    }

    private Algorithm getAlgorithm() {
        byte[] decodeSecret = Base64.getDecoder().decode(apiSecret);
        return Algorithm.HMAC256(new String(decodeSecret));
    }

}
