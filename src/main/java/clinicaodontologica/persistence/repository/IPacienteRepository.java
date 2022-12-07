package clinicaodontologica.persistence.repository;

import clinicaodontologica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("FROM Paciente p WHERE p.nombre LIKE %:name% OR p.apellido LIKE %:name%")
    List<Paciente> findByNameOrSurName(@Param("name") String name);
}
