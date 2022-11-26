package clinicaodontologica.persistence.repository;

import clinicaodontologica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    boolean existsByFecha(LocalDate fecha);
}
