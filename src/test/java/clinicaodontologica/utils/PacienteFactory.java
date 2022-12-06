package clinicaodontologica.utils;

import clinicaodontologica.model.dto.DomicilioDTO;
import clinicaodontologica.model.dto.PacienteDTO;
import clinicaodontologica.persistence.entities.Domicilio;
import clinicaodontologica.persistence.entities.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteFactory {

    public static PacienteDTO createPacienteDTO() {
        return new PacienteDTO("Perez", "Maria", "prueba@email.com", 12212475L,
                LocalDate.of(2022, 12, 05),
                new DomicilioDTO("monroe", 12, "CABA", "Buenos Aires"));

    }

    public static Paciente getPatient() {
        return new Paciente(1L, "Perez", "Maria", "prueba@email.com", 12212475L,
                LocalDate.of(2022, 12, 05),
                new Domicilio(1L, "monroe", 12, "CABA", "Buenos Aires"));

    }

    public static List<PacienteDTO> getAllPatientsDTO() {
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();
        PacienteDTO expected = new PacienteDTO("Perez", "Maria", "prueba@email.com", 12212475L,
                LocalDate.of(2022, 12, 05),
                new DomicilioDTO("monroe", 12, "CABA", "Buenos Aires"));
        pacienteDTOList.add(expected);
        return pacienteDTOList;
    }

    public static List<Paciente> getAllPatients() {
        List<Paciente> pacienteList = new ArrayList<>();
        Paciente paciente = new Paciente(1L, "Perez", "Maria", "prueba@email.com", 12212475L,
                LocalDate.of(2022, 12, 05),
                new Domicilio(1L, "monroe", 12, "CABA", "Buenos Aires"));
        pacienteList.add(paciente);
        return pacienteList;
    }
}
