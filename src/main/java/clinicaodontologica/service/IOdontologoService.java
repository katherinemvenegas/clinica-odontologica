package clinicaodontologica.service;

import clinicaodontologica.model.dto.OdontologoDTO;

import java.util.List;

public interface IOdontologoService {
    String addNewDentist(OdontologoDTO odontologoDTO);
    String modifyDentist(OdontologoDTO odontologoDTO, Long id);
    OdontologoDTO getDentist(Long id);
    List<OdontologoDTO> getAllDentist();
    String deleteDentist(Long id);
}
