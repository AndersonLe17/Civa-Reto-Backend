package pe.com.civa.domain.mapper;

import pe.com.civa.domain.dto.BusResponse;
import pe.com.civa.domain.entities.Bus;

public class BusMapper {

    public static BusResponse busResponseMapper(Bus bus) {
        return BusResponse.builder()
                .busId(bus.getBusId())
                .busNum(bus.getBusNum())
                .busPla(bus.getBusPla())
                .busFecCre(bus.getBusFecCre())
                .busCar(bus.getBusCar())
                .busMar(MarcaMapper.marcaResponseMapper(bus.getBusMar()))
                .busEst(bus.getBusEst())
                .build();
    }

}
