package clinicaodontologica.persistence.repository;

import clinicaodontologica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    @Query("FROM Turno t WHERE t.odontologo.id = :dentista AND t.fecha = :fecha AND t.hora = :hora")
    Turno existByOdontologoAndFechaAndHora(@Param("dentista") Long dentista, @Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora);

    @Query("FROM Turno t WHERE t.fecha = :date ORDER BY t.odontologo.apellido, t.hora")
    List<Turno> findByFechaOrderByOdontologoAndHora(LocalDate date);

}
