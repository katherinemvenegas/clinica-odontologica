package clinicaodontologica.service;

import clinicaodontologica.model.dto.OdontologoDTO;

import java.util.List;

public interface IOdontologoService {
    OdontologoDTO addNewDentist(OdontologoDTO odontologoDTO);

    OdontologoDTO modifyDentist(OdontologoDTO odontologoDTO, Long id);

    OdontologoDTO getDentist(Long id);

    List<OdontologoDTO> getAllDentist();

    String deleteDentist(Long id);
}
