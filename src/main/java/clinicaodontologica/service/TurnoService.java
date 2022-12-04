package clinicaodontologica.service;

import clinicaodontologica.exceptions.ResourceNotFound;
import clinicaodontologica.model.dto.TurnoDTO;
import clinicaodontologica.persistence.entities.Turno;
import clinicaodontologica.persistence.repository.IOdontologoRepository;
import clinicaodontologica.persistence.repository.IPacienteRepository;
import clinicaodontologica.persistence.repository.ITurnoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository iTurnoRepository;
    private IOdontologoRepository iOdontologoRepository;
    private IPacienteRepository iPacienteRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public TurnoService(ITurnoRepository iTurnoRepository, IOdontologoRepository iOdontologoRepository, IPacienteRepository iPacienteRepository) {
        this.iTurnoRepository = iTurnoRepository;
        this.iOdontologoRepository = iOdontologoRepository;
        this.iPacienteRepository = iPacienteRepository;
    }

    @Override
    public String addNewTurn(TurnoDTO turnoDTO) {
        verifyExistDentist(turnoDTO.getIdOdontologo());
        verifyExistPatient(turnoDTO.getIdPaciente());
        dateAvailable(turnoDTO.getFecha());

        Turno turno = new Turno();
        turno.setFecha(turnoDTO.getFecha());
        turno.setOdontologo(iOdontologoRepository.findById(turnoDTO.getIdOdontologo()).orElseThrow());
        turno.setPaciente(iPacienteRepository.findById(turnoDTO.getIdPaciente()).orElseThrow());
        iTurnoRepository.save(turno);

        return "El turno con fecha " + turno.getFecha() + " se creo con exito";
    }

    @Override
    public String modifyTurn(TurnoDTO turnoDTO, Long id) {
        if (!iTurnoRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos el turno que desea modificar");
        } else {
            verifyExistDentist(turnoDTO.getIdOdontologo());
            verifyExistPatient(turnoDTO.getIdPaciente());
            dateAvailable(turnoDTO.getFecha());
            Turno turno = new Turno();
            turno.setId(id);
            turno.setFecha(turnoDTO.getFecha());
            turno.setOdontologo(iOdontologoRepository.findById(turnoDTO.getIdOdontologo()).orElseThrow());
            turno.setPaciente(iPacienteRepository.findById(turnoDTO.getIdPaciente()).orElseThrow());
            iTurnoRepository.save(turno);

            return "El turno se modifico con exito";
        }
    }

    @Override
    public TurnoDTO getTurn(Long id) {
        if (!iTurnoRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos el turno solicitado");
        } else {
            TurnoDTO turnoDTO = modelMapper.map(iTurnoRepository.findById(id).get(), TurnoDTO.class);
            turnoDTO.setPacienteApellido(iTurnoRepository.findById(id).get().getPaciente().getApellido());
            turnoDTO.setOdontologoApellido(iTurnoRepository.findById(id).get().getOdontologo().getApellido());
            return turnoDTO;
        }
    }

    @Override
    public List<TurnoDTO> getAllTurns() {
        List<TurnoDTO> turnoDTOList = iTurnoRepository.findAll().stream().map(turno -> {
            TurnoDTO turnoDTO = modelMapper.map(turno, TurnoDTO.class);
            turnoDTO.setPacienteApellido(turno.getPaciente().getApellido());
            turnoDTO.setOdontologoApellido(turno.getOdontologo().getApellido());

            return turnoDTO;
        }).collect(Collectors.toList());
        return turnoDTOList;
    }

    @Override
    public String deleteTurn(Long id) {
        if (!iTurnoRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos el turno que desea eliminar");
        } else {
            iTurnoRepository.deleteById(id);
            return "El turno fue eliminado con exito";
        }
    }

    //methods support

    private void verifyExistDentist(Long id) {
        if (!iOdontologoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No encontramos el odontologo solicitado");
        } else {
            iOdontologoRepository.findById(id);
        }
        ;
    }


    private void verifyExistPatient(Long id) {
        if (!iPacienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No encontramos el paciente solicitado");
        } else {
            iPacienteRepository.findById(id);
        }
    }

    private void dateAvailable(LocalDate fecha) {
        if (iTurnoRepository.existsByFecha(fecha) || fecha.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha solicitada no se encuentra disponible");
        }
        ;
    }
}
