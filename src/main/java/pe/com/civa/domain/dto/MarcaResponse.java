package pe.com.civa.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link pe.com.civa.domain.entities.Marca}
 */
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class MarcaResponse implements Serializable {

    private Long marId;

    private String marNom;

}