package clinicaodontologica.service;

import clinicaodontologica.model.dto.TurnoDTO;
import clinicaodontologica.persistence.entities.Turno;
import clinicaodontologica.persistence.repository.IOdontologoRepository;
import clinicaodontologica.persistence.repository.IPacienteRepository;
import clinicaodontologica.persistence.repository.ITurnoRepository;
import clinicaodontologica.utils.OdontologoFactory;
import clinicaodontologica.utils.PacienteFactory;
import clinicaodontologica.utils.TurnoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TurnoServiceTest {

    @Mock
    private ITurnoRepository iTurnoRepository;

    @Mock
    private IOdontologoRepository iOdontologoRepository;

    @Mock
    private IPacienteRepository iPacienteRepository;

    @InjectMocks
    private TurnoService turnoService;


    @Test
    @DisplayName("Create turn successfully")
    void addNewTurn() {
        //arrange
        TurnoDTO expected = TurnoFactory.createTurnoDTO();

        //act
        when(iPacienteRepository.existsById(1L)).thenReturn(true);
        when(iPacienteRepository.findById(1L)).thenReturn(Optional.of(PacienteFactory.getPatient()));

        when(iOdontologoRepository.existsById(1L)).thenReturn(true);
        when(iOdontologoRepository.findById(1L)).thenReturn(Optional.of(OdontologoFactory.getDentist()));

        TurnoDTO actual = turnoService.addNewTurn(expected);

        //assert
        verify(iTurnoRepository, times(1)).save(any());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("Update turn successfully")
    void modifyTurn() {
        //arrange
        TurnoDTO expected = TurnoFactory.createTurnoDTO();

        //act
        when(iTurnoRepository.existsById(1L)).thenReturn(true);

        when(iPacienteRepository.existsById(1L)).thenReturn(true);
        when(iPacienteRepository.findById(1L)).thenReturn(Optional.of(PacienteFactory.getPatient()));

        when(iOdontologoRepository.existsById(1L)).thenReturn(true);
        when(iOdontologoRepository.findById(1L)).thenReturn(Optional.of(OdontologoFactory.getDentist()));

        TurnoDTO actual = turnoService.modifyTurn(expected, 1L);

        //assert
        verify(iTurnoRepository, times(1)).save(any());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("Get turn by Id successfully")
    void getTurn() {
        //arrange
        Turno turn = TurnoFactory.getTurn();
        TurnoDTO expected = TurnoFactory.createTurnoDTO();

        //act
        when(iPacienteRepository.findById(1L)).thenReturn(Optional.of(PacienteFactory.getPatient()));

        when(iOdontologoRepository.findById(1L)).thenReturn(Optional.of(OdontologoFactory.getDentist()));

        when(iTurnoRepository.existsById(1L)).thenReturn(true);
        when(iTurnoRepository.findById(1L)).thenReturn(Optional.of(turn));

        TurnoDTO actual = turnoService.getTurn(1L);

        //assert
        verify(iTurnoRepository, times(1)).findById(1L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("GetAll turn successfully")
    void getAllTurns() {
        //arrange
        List<Turno> turnoList = TurnoFactory.getAllTurns();
        List<TurnoDTO> expected = TurnoFactory.getAllTurnsDTO();

        //act
        when(iTurnoRepository.findAll()).thenReturn(turnoList);
        List<TurnoDTO> actual = turnoService.getAllTurns();

        //assert
        verify(iTurnoRepository, times(1)).findAll();
        assertEquals(expected.size(), actual.size());

    }

    @Test
    @DisplayName("Delete turn by Id successfully")
    void deleteTurn() {
        //act
        when(iTurnoRepository.existsById(1L)).thenReturn(true);
        turnoService.deleteTurn(1L);
        //assert
        verify(iTurnoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Get tuns by date successfully")
    void getTurnsByDate() {
        //arrange
        List<Turno> turnoList = TurnoFactory.getAllTurns();
        List<TurnoDTO> expected = TurnoFactory.getAllTurnsDTO();

        //act
        when(iTurnoRepository.findByFechaOrderByOdontologoAndHora(LocalDate.of(2022, 12, 12))).thenReturn(turnoList);
        List<TurnoDTO> actual = turnoService.getTurnsByDate(LocalDate.of(2022, 12, 12));

        //assert
        verify(iTurnoRepository, times(1)).findByFechaOrderByOdontologoAndHora(LocalDate.of(2022, 12, 12));
        assertEquals(expected.size(), actual.size());

    }
}