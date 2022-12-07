package clinicaodontologica.service;

import clinicaodontologica.model.dto.TurnoDTO;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {

    TurnoDTO addNewTurn(TurnoDTO turnoDTO);

    TurnoDTO modifyTurn(TurnoDTO turnoDTO, Long id);

    TurnoDTO getTurn(Long id);

    List<TurnoDTO> getAllTurns();

    List<TurnoDTO> getTurnsByDate(LocalDate date);

    String deleteTurn(Long id);
}
