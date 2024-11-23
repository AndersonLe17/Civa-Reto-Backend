package pe.com.civa.config.response;


import lombok.*;

import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class EntityResponse<T> {
    private Integer code;
    private String status;
    private String message;
    private T payload;
    private List<ErrorResponse> errors;
}
