package com.totospz.eshop.config.exception;

import com.totospz.eshop.config.response.ErrorResponse;
import com.totospz.eshop.config.response.ResponseHttp;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseHttp.notFound(
                Arrays.asList(ErrorResponse.builder()
                        .error(ex.getProperty())
                        .msg(ex.getMessage())
                        .build()
                )
        );
    }

    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity handleJwtAuthException(JwtAuthException ex) {
        return ResponseHttp.forbidden(
                Arrays.asList(ErrorResponse.builder()
                        .error("JWT Exception")
                        .msg(ex.getMessage())
                        .build()
                )
        );
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorResponse> errors = ex.getBindingResult().getAllErrors().stream().map(error ->
                ErrorResponse.builder().error(((FieldError) error).getField()).msg(error.getDefaultMessage()).build()
        ).toList();
        return ResponseHttp.badRequest(errors);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleBusinessValidations(ValidationException ex) {
        return ResponseHttp.badRequest(
                Arrays.asList(ErrorResponse.builder()
                        .error("Business Validation")
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
