package pe.com.civa.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import pe.com.civa.config.response.EntityResponse;
import pe.com.civa.config.response.ErrorResponse;

import java.io.IOException;
import java.util.List;

public class ExceptionManagerFilter {

    public static void responseExceptionFilter(HttpServletResponse response, Exception ex) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(response.getOutputStream(),
                EntityResponse.builder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .status(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .message("Denied")
                        .errors(List.of(ErrorResponse.builder().error("Unauthorized").msg(getMessageException(ex)).build()))
                        .build()
        );
    }

    private static String getMessageException(Exception ex) {
        switch (ex.getClass().getSimpleName()) {
            case "SignatureVerificationException":
                return "El token es inválido.";
            case "TokenExpiredException":
                return "El token expiró.";
            default: {
                return ex.getMessage();
            }
        }
    }

}
