package clinicaodontologica.service;

import clinicaodontologica.model.dto.TurnoDTO;

import java.util.List;

public interface ITurnoService {

    String addNewTurn(TurnoDTO turnoDTO);
    String modifyTurn(TurnoDTO turnoDTO, Long id);
    TurnoDTO getTurn(Long id);
    List<TurnoDTO> getAllTurns();
    String deleteTurn(Long id);
}
