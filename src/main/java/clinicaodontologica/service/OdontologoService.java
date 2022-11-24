package clinicaodontologica.service;


import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.persistence.entities.Odontologo;
import clinicaodontologica.persistence.repository.IOdontologoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IOdontologoRepository iOdontologoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public OdontologoService() {
    }
    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }

    public String addNewDentist(OdontologoDTO odontologoDTO){
        System.out.println(odontologoDTO);

        Odontologo odontologo = modelMapper.map(odontologoDTO, Odontologo.class);
        System.out.println(odontologo);
        iOdontologoRepository.save(odontologo);
        return "El odontologo " + odontologo.getApellido() + " se creo con exito";
    }

    public String modifyDentist(Odontologo odontologo, Long id){
        odontologo.setId(id);
        iOdontologoRepository.save(odontologo);
        return "El odontologo " + odontologo.getApellido() + " se modifico con exito";
    }

    public Odontologo getDentist(Long id){
        return iOdontologoRepository.findById(id).get();
    }

    public List<Odontologo> getAllDentist(){
        return iOdontologoRepository.findAll();
    }

    public String deleteDentist(Long id){
        iOdontologoRepository.deleteById(id);
        return "El odontologo fue eliminado con exito";
    }
}
