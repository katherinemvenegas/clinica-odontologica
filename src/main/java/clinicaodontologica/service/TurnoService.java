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
import java.time.LocalTime;
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
    public TurnoDTO addNewTurn(TurnoDTO turnoDTO) {
        verifyExistDentist(turnoDTO.getIdOdontologo());
        verifyExistPatient(turnoDTO.getIdPaciente());
        dateOfTurnAvailableByDentist(turnoDTO.getIdOdontologo(), turnoDTO.getFecha(), turnoDTO.getHora());

        Turno turno = new Turno();
        turno.setFecha(turnoDTO.getFecha());
        turno.setHora(turnoDTO.getHora());
        turno.setOdontologo(iOdontologoRepository.findById(turnoDTO.getIdOdontologo()).orElseThrow());
        turno.setPaciente(iPacienteRepository.findById(turnoDTO.getIdPaciente()).orElseThrow());
        iTurnoRepository.save(turno);

        TurnoDTO dto = modelMapper.map(turno, TurnoDTO.class);
        return dto;
    }

    @Override
    public TurnoDTO modifyTurn(TurnoDTO turnoDTO, Long id) {
        if (!iTurnoRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos el turno que desea modificar");
        } else {
            verifyExistDentist(turnoDTO.getIdOdontologo());
            verifyExistPatient(turnoDTO.getIdPaciente());
            dateOfTurnAvailableByDentist(turnoDTO.getIdOdontologo(), turnoDTO.getFecha(), turnoDTO.getHora());
            Turno turno = new Turno();
            turno.setId(id);
            turno.setFecha(turnoDTO.getFecha());
            turno.setHora(turnoDTO.getHora());
            turno.setOdontologo(iOdontologoRepository.findById(turnoDTO.getIdOdontologo()).orElseThrow());
            turno.setPaciente(iPacienteRepository.findById(turnoDTO.getIdPaciente()).orElseThrow());
            iTurnoRepository.save(turno);

            TurnoDTO dto = modelMapper.map(turno, TurnoDTO.class);
            return dto;
        }
    }

    @Override
    public TurnoDTO getTurn(Long id) {
        if (!iTurnoRepository.existsById(id)) {
            throw new ResourceNotFound("No encontramos el turno solicitado");
        } else {
            TurnoDTO turnoDTO = modelMapper.map(iTurnoRepository.findById(id).get(), TurnoDTO.class);
            turnoDTO.setPacienteApellido(iPacienteRepository.findById(turnoDTO.getIdPaciente()).get().getApellido());
            turnoDTO.setOdontologoApellido(iOdontologoRepository.findById(turnoDTO.getIdOdontologo()).get().getApellido());
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
    public List<TurnoDTO> getTurnsByDate(LocalDate date) {
        List<TurnoDTO> turnoDTOList = iTurnoRepository.findByFechaOrderByOdontologoAndHora(date).stream().map(turno -> {
            TurnoDTO turnoDTO = modelMapper.map(turno, TurnoDTO.class);
            turnoDTO.setPacienteApellido(turno.getPaciente().getApellido());
            turnoDTO.setOdontologoApellido(turno.getOdontologo().getApellido());

            return turnoDTO;
        }).collect(Collectors.toList());
        if (turnoDTOList.isEmpty()) {
            throw new ResourceNotFound("No encontramos turnos en la fecha indicada");
        } else {
            return turnoDTOList;
        }
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
    }


    private void verifyExistPatient(Long id) {
        if (!iPacienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No encontramos el paciente solicitado");
        } else {
            iPacienteRepository.findById(id);
        }
    }

    private void dateOfTurnAvailableByDentist(Long idOdontologo, LocalDate fecha, LocalTime hora) {
        if (iTurnoRepository.existByOdontologoAndFechaAndHora(idOdontologo, fecha, hora) != null || fecha.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha solicitada no se encuentra disponible");
        }
    }
}
