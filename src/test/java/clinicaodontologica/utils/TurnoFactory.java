package clinicaodontologica.utils;

import clinicaodontologica.model.dto.TurnoDTO;
import clinicaodontologica.persistence.entities.Odontologo;
import clinicaodontologica.persistence.entities.Paciente;
import clinicaodontologica.persistence.entities.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TurnoFactory {

    public static TurnoDTO createTurnoDTO() {
        return new TurnoDTO(1L, 1L, LocalDate.of(2022, 12, 31), LocalTime.of(12, 30));
    }

    public static Turno getTurn() {
        Paciente paciente = PacienteFactory.getPatient();
        Odontologo odontologo = OdontologoFactory.getDentist();
        return new Turno(1L, paciente, odontologo, LocalDate.of(2022, 12, 31), LocalTime.of(12, 30));
    }

    public static List<Turno> getAllTurns() {
        List<Turno> turnoList = new ArrayList<>();
        turnoList.add(getTurn());
        return turnoList;
    }

    public static List<TurnoDTO> getAllTurnsDTO() {
        List<TurnoDTO> turnoDTOList = new ArrayList<>();
        turnoDTOList.add(createTurnoDTO());
        return turnoDTOList;
    }
}
