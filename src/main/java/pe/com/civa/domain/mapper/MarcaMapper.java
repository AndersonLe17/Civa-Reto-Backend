package pe.com.civa.domain.mapper;

import pe.com.civa.domain.dto.MarcaResponse;
import pe.com.civa.domain.entities.Marca;

public class MarcaMapper {

    public static MarcaResponse marcaResponseMapper(Marca busMar) {
        return MarcaResponse.builder()
                .marId(busMar.getMarId())
                .marNom(busMar.getMarNom())
                .build();
    }

}
