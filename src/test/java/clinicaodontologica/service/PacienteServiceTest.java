package clinicaodontologica.service;

import clinicaodontologica.model.dto.PacienteDTO;
import clinicaodontologica.persistence.entities.Paciente;
import clinicaodontologica.persistence.repository.IPacienteRepository;
import clinicaodontologica.utils.PacienteFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {


    @Mock
    private IPacienteRepository iPacienteRepository;
    @InjectMocks
    private PacienteService pacienteService;

    @Test
    @DisplayName("Create patient successfully")
    void addNewPatient() {
        //arrange
        PacienteDTO expected = PacienteFactory.createPacienteDTO();

        //act
        PacienteDTO actual = pacienteService.addNewPatient(expected);

        //assert
        verify(iPacienteRepository, times(1)).save(any());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("Update patient successfully")
    void modifyPatient() {
        //arrange
        PacienteDTO expected = PacienteFactory.createPacienteDTO();
        //act
        when(iPacienteRepository.existsById(1L)).thenReturn(true);
        PacienteDTO actual = pacienteService.modifyPatient(expected, 1L);

        //assert
        verify(iPacienteRepository, times(1)).save(any());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("GetPatient by Id successfully")
    void getPatient() {
        //arrange
        Paciente paciente = PacienteFactory.getPatient();
        PacienteDTO expected = PacienteFactory.createPacienteDTO();
        //act
        when(iPacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(iPacienteRepository.existsById(1L)).thenReturn(true);
        PacienteDTO actual = pacienteService.getPatient(1L);

        //assert
        verify(iPacienteRepository, times(1)).findById(1L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("GetAll successfully")
    void getAllPatients() {
        //arrange
        List<Paciente> pacienteList = PacienteFactory.getAllPatients();

        List<PacienteDTO> expected = PacienteFactory.getAllPatientsDTO();

        //act
        when(iPacienteRepository.findAll()).thenReturn(pacienteList);
        List<PacienteDTO> actual = pacienteService.getAllPatients();

        //assert
        verify(iPacienteRepository, times(1)).findAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    @DisplayName("Delete patient by id successfully")
    void deletePatient() {
        //arrange

        //act
        when(iPacienteRepository.existsById(1L)).thenReturn(true);
        pacienteService.deletePatient(1L);

        //assert
        verify(iPacienteRepository, times(1)).deleteById(any());
    }
}