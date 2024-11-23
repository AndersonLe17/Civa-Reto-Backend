package pe.com.civa.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class AuthResponse {

    private String jwt;

}
