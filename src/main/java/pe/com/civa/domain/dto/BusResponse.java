package pe.com.civa.domain.dto;

import lombok.*;
import pe.com.civa.domain.entities.Marca;
import pe.com.civa.domain.enums.Estado;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link pe.com.civa.domain.entities.Bus}
 */

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class BusResponse implements Serializable {

    private Long busId;

    private String busNum;

    private String busPla;

    private LocalDateTime busFecCre;

    private String busCar;

    private MarcaResponse busMar;

    private Estado busEst;

}