package pe.com.civa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.civa.domain.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuNomIgnoreCase(String usuNom);
}