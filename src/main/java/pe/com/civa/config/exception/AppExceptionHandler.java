package pe.com.civa.config.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.com.civa.config.response.ErrorResponse;
import pe.com.civa.config.response.ResponseHttp;

import java.util.Arrays;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseHttp.notFound(
                Arrays.asList(ErrorResponse.builder()
                        .error("Entity Not Found")
                        .msg(ex.getMessage())
                        .build()
                )
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleUsernameNotFoundException(BadCredentialsException ex) {
        return ResponseHttp.unauthorized(
                Arrays.asList(ErrorResponse.builder()
                        .error("Auth Validation")
                        .msg("El nombre de Usuario y/o Contraseña son incorrectos.")
                        .build()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception ex, WebRequest webRequest) {
        return ResponseHttp.internalServerError(
                Arrays.asList(ErrorResponse.builder()
                        .error(webRequest.getDescription(false))
                        .msg(ex.getMessage())
                        .build()
                )
        );
    }

}
