package clinicaodontologica.service;

import clinicaodontologica.model.dto.PacienteDTO;

import java.util.List;

public interface IPacienteService {
    String addNewPatient(PacienteDTO pacienteDTO);
    String modifyPatient(PacienteDTO pacienteDTO, Long id);
    PacienteDTO getPatient(Long id);
    List<PacienteDTO> getAllPatients();
    String deletePatient(Long id);
}
