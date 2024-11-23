package pe.com.civa.config.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHttp {

    public static <T> ResponseEntity ok(T payload) {
        return new ResponseEntity<>(
                EntityResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .message("Success")
                        .payload(payload)
                        .build(),
                HttpStatus.OK
        );
    }

    public static <T> ResponseEntity badRequest(List<ErrorResponse> errors) {
        return new ResponseEntity<>(
                EntityResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message("Failure")
                        .errors(errors)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static <T> ResponseEntity notFound(List<ErrorResponse> errors) {
        return new ResponseEntity<>(
                EntityResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message("Failure")
                        .errors(errors)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    public static <T> ResponseEntity forbidden(List<ErrorResponse> errors) {
        return new ResponseEntity<>(
                EntityResponse.builder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .status(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .message("Denied")
                        .errors(errors)
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    public static <T> ResponseEntity unauthorized(List<ErrorResponse> errors) {
        return new ResponseEntity<>(
                EntityResponse.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .status(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        .message("Denied")
                        .errors(errors)
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    public static <T> ResponseEntity internalServerError(List<ErrorResponse> errors) {
        return new ResponseEntity<>(
                EntityResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message("Failure")
                        .errors(errors)
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
