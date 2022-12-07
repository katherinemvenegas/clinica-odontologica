package clinicaodontologica.service;

import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.persistence.entities.Odontologo;
import clinicaodontologica.persistence.repository.IOdontologoRepository;
import clinicaodontologica.utils.OdontologoFactory;
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
class OdontologoServiceTest {

    @Mock
    private IOdontologoRepository iOdontologoRepository;

    @InjectMocks
    private OdontologoService odontologoService;

    @Test
    @DisplayName("Create dentist successfully")
    void addNewDentist() {
        //arrange
        OdontologoDTO expected = OdontologoFactory.createOdontologoDTO();

        //act
        OdontologoDTO actual = odontologoService.addNewDentist(expected);

        //assert
        verify(iOdontologoRepository, times(1)).save(any());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("Update dentist successfully")
    void modifyDentist() {
        //arrange
        OdontologoDTO expected = OdontologoFactory.createOdontologoDTO();

        //act
        when(iOdontologoRepository.existsById(1L)).thenReturn(true);
        OdontologoDTO actual = odontologoService.modifyDentist(expected, 1L);

        //assert

        verify(iOdontologoRepository, times(1)).save(any());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("GetDentist by Id successfully")
    void getDentist() {
        //arrange
        Odontologo odontologo = OdontologoFactory.getDentist();
        OdontologoDTO expected = OdontologoFactory.createOdontologoDTO();

        //act
        when(iOdontologoRepository.existsById(1L)).thenReturn(true);
        when(iOdontologoRepository.findById(1L)).thenReturn(Optional.of(odontologo));
        OdontologoDTO actual = odontologoService.getDentist(1L);

        //assert

        verify(iOdontologoRepository, times(1)).findById(1L);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("GetAll successfully")
    void getAllDentist() {
        //arrange
        List<Odontologo> odontologoList = OdontologoFactory.getAllDentist();
        List<OdontologoDTO> expected = OdontologoFactory.getAllDentistDTO();

        //act
        when(iOdontologoRepository.findAll()).thenReturn(odontologoList);
        List<OdontologoDTO> actual = odontologoService.getAllDentist();

        //assert
        verify(iOdontologoRepository, times(1)).findAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    @DisplayName("Delete dentist by Id successfully")
    void deleteDentist() {
        //arrange

        //act
        when(iOdontologoRepository.existsById(1L)).thenReturn(true);
        odontologoService.deleteDentist(1L);

        //assert
        verify(iOdontologoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Get dentist by matricula successfully")
    void getDentistByMatricula() {
        //arrange
        Odontologo odontologo = OdontologoFactory.getDentist();
        OdontologoDTO expected = OdontologoFactory.createOdontologoDTO();

        //act
        when(iOdontologoRepository.existsByMatricula("ABC123")).thenReturn(true);
        when(iOdontologoRepository.findByMatricula("ABC123")).thenReturn(odontologo);
        OdontologoDTO actual = odontologoService.getDentistByMatricula("ABC123");

        //assert

        verify(iOdontologoRepository, times(1)).findByMatricula("ABC123");
        assertEquals(expected.toString(), actual.toString());
    }
}