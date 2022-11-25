package clinicaodontologica.controller;

import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.service.OdontologoService;
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
    public OdontologoDTO getById(@PathVariable Long id){
        return odontologoService.getDentist(id);
    }

    @GetMapping()

    public List<OdontologoDTO> getAll(){
        return odontologoService.getAllDentist();
    }

    @PostMapping("")
    public String newDentist(@RequestBody OdontologoDTO odontologoDTO){
        odontologoService.addNewDentist(odontologoDTO);
        return "Se registró correctamente";
    }

    @PutMapping("{id}")
    public String modifyDentist(@PathVariable Long id, @RequestBody OdontologoDTO odontologoDTO){
        odontologoService.modifyDentist(odontologoDTO, id);
        return "Se editó correctamente";
    }

    @DeleteMapping("{id}")
    public String deleteDentist(@PathVariable Long id){
        odontologoService.deleteDentist(id);
        return "Se elimino correctamente el registro: " + id;
    }
}
