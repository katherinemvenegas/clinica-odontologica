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

    @GetMapping("buscarPorId")
    public OdontologoDTO buscarPorId(@RequestParam Long id){
        return odontologoService.getDentist(id);
    }

    @GetMapping()

    public List<OdontologoDTO> buscarTodos(){
        return odontologoService.getAllDentist();
    }

    @PostMapping("nuevoOdontologo")
    public String nuevoOdontologo(@RequestBody OdontologoDTO odontologoDTO){
        odontologoService.addNewDentist(odontologoDTO);
        return "Se registró correctamente";
    }

    @PutMapping("editarOdontologo")
    public String editarOdontologo(@RequestParam Long id, @RequestBody OdontologoDTO odontologoDTO){
        odontologoService.modifyDentist(odontologoDTO, id);
        return "Se editó correctamente";
    }
}