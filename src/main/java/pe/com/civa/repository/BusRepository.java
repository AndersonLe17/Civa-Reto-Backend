package pe.com.civa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.civa.domain.entities.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
}