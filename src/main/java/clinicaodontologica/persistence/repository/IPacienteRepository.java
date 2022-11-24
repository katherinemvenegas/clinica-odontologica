package clinicaodontologica.persistence.repository;

import clinicaodontologica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}
