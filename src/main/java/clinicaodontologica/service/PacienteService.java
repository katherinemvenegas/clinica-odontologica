package clinicaodontologica.service;

import clinicaodontologica.model.dto.PacienteDTO;
import clinicaodontologica.persistence.entities.Paciente;
import clinicaodontologica.persistence.repository.IPacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private IPacienteRepository iPacienteRepository;

    private ModelMapper modelMapper;

    public PacienteService(IPacienteRepository iPacienteRepository, ModelMapper modelMapper) {
        this.iPacienteRepository = iPacienteRepository;
        this.modelMapper = modelMapper;
    }

    public String addNewPatient(PacienteDTO pacienteDTO){

        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        iPacienteRepository.save(paciente);
        return "El paciente " + paciente.getApellido() + " se creo con exito";
    }

    public String modifyPatient(PacienteDTO pacienteDTO, Long id){
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        paciente.setId(id);
        iPacienteRepository.save(paciente);
        return "El paciente" + paciente.getApellido() + " se modifico con exito";
    }

    public PacienteDTO getPatient(Long id){
        PacienteDTO pacienteDTO = modelMapper.map( iPacienteRepository.findById(id), PacienteDTO.class);
        return pacienteDTO;
    }

    public List<PacienteDTO> getAllPatients(){
        List<PacienteDTO> pacienteDTOList = iPacienteRepository.findAll()
                .stream().map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                .collect(Collectors.toList());
        return pacienteDTOList;
    }

    public String deletePatient(Long id){
        iPacienteRepository.deleteById(id);
        return "El paciente fue eliminado con exito";
    }
}
