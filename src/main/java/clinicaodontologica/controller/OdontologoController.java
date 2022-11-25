package clinicaodontologica.controller;

import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos/v1/")
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<OdontologoDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.getDentist(id), HttpStatus.OK);
    }

    @GetMapping()

    public ResponseEntity<List<OdontologoDTO>> getAll(){
        return new ResponseEntity<>(odontologoService.getAllDentist(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> newDentist(@RequestBody OdontologoDTO odontologoDTO){
        odontologoService.addNewDentist(odontologoDTO);
        return new ResponseEntity<>("Se registró correctamente", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> modifyDentist(@PathVariable Long id, @RequestBody OdontologoDTO odontologoDTO){
        odontologoService.modifyDentist(odontologoDTO, id);
        return new ResponseEntity<>("Se editó correctamente", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id){
        odontologoService.deleteDentist(id);
        return new ResponseEntity<>("Se elimino correctamente el registro: " + id, HttpStatus.OK);
    }
}
