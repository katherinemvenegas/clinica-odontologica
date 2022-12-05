package clinicaodontologica.controller;

import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos/v1/")
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<OdontologoDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.getDentist(id), HttpStatus.OK);
    }

    @GetMapping()

    public ResponseEntity<List<OdontologoDTO>> getAll() {
        return new ResponseEntity<>(odontologoService.getAllDentist(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OdontologoDTO> newDentist(@RequestBody @Valid OdontologoDTO odontologoDTO) {
        return new ResponseEntity<>(odontologoService.addNewDentist(odontologoDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<OdontologoDTO> modifyDentist(@PathVariable Long id, @RequestBody @Valid OdontologoDTO odontologoDTO) {
        return new ResponseEntity<>(odontologoService.modifyDentist(odontologoDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.deleteDentist(id), HttpStatus.OK);
    }
}
