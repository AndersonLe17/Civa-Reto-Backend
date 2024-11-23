package pe.com.civa.service;

import org.springframework.data.domain.Pageable;
import pe.com.civa.config.response.PaginationResponse;
import pe.com.civa.domain.dto.BusResponse;

import java.net.URI;
import java.net.URISyntaxException;

public interface BusService {

    PaginationResponse getBuses(Pageable pageable, URI uri) throws URISyntaxException;

    BusResponse getBusById(Long id);
}
