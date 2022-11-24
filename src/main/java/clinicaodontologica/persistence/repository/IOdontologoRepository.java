package clinicaodontologica.persistence.repository;

import clinicaodontologica.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
}
