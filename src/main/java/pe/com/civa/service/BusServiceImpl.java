package pe.com.civa.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.civa.config.response.PaginationResponse;
import pe.com.civa.domain.dto.BusResponse;
import pe.com.civa.domain.entities.Bus;
import pe.com.civa.domain.mapper.BusMapper;
import pe.com.civa.domain.mapper.PaginationMapper;
import pe.com.civa.repository.BusRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse getBuses(Pageable pageable, URI uri) throws URISyntaxException {
        Page<Bus> page = busRepository.findAll(pageable);
        List<BusResponse> data = page.map(BusMapper::busResponseMapper).stream().toList();

        return PaginationMapper.paginationResponseMapper(data, page, uri);
    }

    @Override
    @Transactional(readOnly = true)
    public BusResponse getBusById(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El bus con el id " + id + " no existe."));
        return BusMapper.busResponseMapper(bus);
    }

}
