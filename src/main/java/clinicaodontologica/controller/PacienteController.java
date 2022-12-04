package clinicaodontologica.controller;

import clinicaodontologica.model.dto.PacienteDTO;
import clinicaodontologica.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes/v1/")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.getPatient(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PacienteDTO>> getAll(){
        return new ResponseEntity<>(pacienteService.getAllPatients(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> newPatient(@RequestBody @Valid PacienteDTO pacienteDTO){

        return new ResponseEntity<>(pacienteService.addNewPatient(pacienteDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> modifyPatient(@PathVariable Long id, @RequestBody @Valid PacienteDTO pacienteDTO){

        return new ResponseEntity<>(pacienteService.modifyPatient(pacienteDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){

        return new ResponseEntity<>(pacienteService.deletePatient(id), HttpStatus.OK);
    }
}
