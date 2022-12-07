package clinicaodontologica.service;

import clinicaodontologica.model.dto.PacienteDTO;

import java.util.List;

public interface IPacienteService {
    PacienteDTO addNewPatient(PacienteDTO pacienteDTO);

    PacienteDTO modifyPatient(PacienteDTO pacienteDTO, Long id);

    PacienteDTO getPatient(Long id);

    List<PacienteDTO> getPatientsByNameOrSurName(String name);

    List<PacienteDTO> getAllPatients();

    String deletePatient(Long id);
}
