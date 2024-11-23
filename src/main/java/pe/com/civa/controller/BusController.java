package pe.com.civa.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.civa.config.response.ResponseHttp;
import pe.com.civa.service.BusService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    @GetMapping
    public ResponseEntity<?> getBuses(@PageableDefault Pageable pageable, HttpServletRequest req) throws URISyntaxException {
        URI uri = new URI(req.getRequestURI().concat(req.getQueryString() != null ? "?" + req.getQueryString() : ""));
        return ResponseHttp.ok(busService.getBuses(pageable, uri));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'OPERADOR')")
    public ResponseEntity<?> getBusById(@PathVariable Long id) {
        return ResponseHttp.ok(busService.getBusById(id));
    }

}
