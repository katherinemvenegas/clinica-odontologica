package clinicaodontologica.service;

import clinicaodontologica.model.dto.TurnoDTO;

import java.util.List;

public interface ITurnoService {

    TurnoDTO addNewTurn(TurnoDTO turnoDTO);

    TurnoDTO modifyTurn(TurnoDTO turnoDTO, Long id);

    TurnoDTO getTurn(Long id);

    List<TurnoDTO> getAllTurns();

    String deleteTurn(Long id);
}
