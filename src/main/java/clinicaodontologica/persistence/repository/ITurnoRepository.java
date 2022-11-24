package clinicaodontologica.persistence.repository;

import clinicaodontologica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
