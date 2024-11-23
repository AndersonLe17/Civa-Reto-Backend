package pe.com.civa.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.com.civa.config.cookie.CookieUtil;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = CookieUtil.getValueFromCookie(request, "jwt");

            if (request.getRequestURI().endsWith("/login")) {}
            else if (token != null) {
                String subject = jwtService.getSubject(token);
                CustomUserDetails userDetails = jwtService.userDetails(subject);

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

                String newJWT = jwtService.generateToken(userDetails.getUsuario());
                CookieUtil.create(response, "jwt", newJWT, true, true, jwtService.getExpiredSeconds(newJWT), "/");
            } else throw new IllegalArgumentException("No se ha enviado el token.");

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            ExceptionManagerFilter.responseExceptionFilter(response, e);
        }
    }

}
