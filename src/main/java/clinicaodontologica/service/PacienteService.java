package clinicaodontologica.service;

import clinicaodontologica.exceptions.ResourceNotFound;
import clinicaodontologica.model.dto.PacienteDTO;
import clinicaodontologica.persistence.entities.Paciente;
import clinicaodontologica.persistence.repository.IPacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository iPacienteRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public PacienteService(IPacienteRepository iPacienteRepository) {
        this.iPacienteRepository = iPacienteRepository;
    }

    @Override
    public PacienteDTO addNewPatient(PacienteDTO pacienteDTO) {

        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        iPacienteRepository.save(paciente);

        PacienteDTO dto = modelMapper.map(paciente, PacienteDTO.class);
        return dto;
    }

    @Override
    public PacienteDTO modifyPatient(PacienteDTO pacienteDTO, Long id) {
        if (!iPacienteRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos al paciente que desea modificar");
        } else {
            Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
            paciente.setId(id);
            iPacienteRepository.save(paciente);

            PacienteDTO dto = modelMapper.map(paciente, PacienteDTO.class);
            return dto;
        }
    }

    @Override
    public PacienteDTO getPatient(Long id) {
        if (!iPacienteRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos al paciente solicitado");
        } else {
            PacienteDTO pacienteDTO = modelMapper.map(iPacienteRepository.findById(id).get(), PacienteDTO.class);
            return pacienteDTO;
        }
    }

    @Override
    public List<PacienteDTO> getPatientsByNameOrSurName(String name) {

        List<PacienteDTO> dtoList = iPacienteRepository.findByNameOrSurName(name)
                .stream().map(paciente -> {
                    PacienteDTO pacienteDTO = modelMapper.map(paciente, PacienteDTO.class);
                    return pacienteDTO;
                }).collect(Collectors.toList());

        if (dtoList.isEmpty()) {
            throw new ResourceNotFound("No encontramos al paciente solicitado");
        } else {
            return dtoList;
        }


    }

    @Override
    public List<PacienteDTO> getAllPatients() {
        List<PacienteDTO> pacienteDTOList = iPacienteRepository.findAll().
                stream().map(paciente -> {
                    PacienteDTO pacienteDTO = modelMapper.map(paciente, PacienteDTO.class);
                    return pacienteDTO;
                })
                .collect(Collectors.toList());
        return pacienteDTOList;
    }

    @Override
    public String deletePatient(Long id) {
        if (!iPacienteRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos al paciente que desea eliminar");
        } else {
            iPacienteRepository.deleteById(id);
            return "El paciente fue eliminado con exito";
        }
    }
}
