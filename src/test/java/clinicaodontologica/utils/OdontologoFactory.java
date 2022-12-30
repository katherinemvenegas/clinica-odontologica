package clinicaodontologica.utils;

import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.persistence.entities.Odontologo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OdontologoFactory {

    public static OdontologoDTO createOdontologoDTO() {
        return new OdontologoDTO("ABC123", "Maria", "Gonzalez");
    }

    public static OdontologoDTO createOdontologoDTOFail() {
        return new OdontologoDTO("ABC1", "Maria", "Gonzalez");
    }

    public static Odontologo getDentist() {
        return new Odontologo(1L, "ABC123", "Maria", "Gonzalez", new HashSet<>());
    }

    public static List<Odontologo> getAllDentist() {
        List<Odontologo> odontologoList = new ArrayList<>();
        Odontologo odontologo = new Odontologo(1L, "ABC123", "Maria", "Gonzalez", new HashSet<>());
        odontologoList.add(odontologo);
        return odontologoList;
    }

    public static List<OdontologoDTO> getAllDentistDTO() {
        List<OdontologoDTO> odontologoDTOList = new ArrayList<>();
        OdontologoDTO dto = new OdontologoDTO("ABC123", "Maria", "Gonzalez");
        odontologoDTOList.add(dto);
        return odontologoDTOList;
    }
}
