package clinicaodontologica.controller;

import clinicaodontologica.model.dto.TurnoDTO;
import clinicaodontologica.service.TurnoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos/v1/")
public class TurnoController {

    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<TurnoDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.getTurn(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TurnoDTO>> getAll() {
        return new ResponseEntity<>(turnoService.getAllTurns(), HttpStatus.OK);
    }

    @GetMapping("fecha")
    public ResponseEntity<List<TurnoDTO>> getByDate(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate d) {
        return new ResponseEntity<>(turnoService.getTurnsByDate(d), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TurnoDTO> newTurn(@RequestBody @Valid TurnoDTO turnoDTO) {
        return new ResponseEntity<>(turnoService.addNewTurn(turnoDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TurnoDTO> modifyTurn(@PathVariable Long id, @RequestBody @Valid TurnoDTO turnoDTO) {
        return new ResponseEntity<>(turnoService.modifyTurn(turnoDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTurn(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.deleteTurn(id), HttpStatus.OK);
    }

}
