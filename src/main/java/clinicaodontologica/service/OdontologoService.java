package clinicaodontologica.service;


import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.persistence.entities.Odontologo;
import clinicaodontologica.persistence.repository.IOdontologoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OdontologoService {
    private IOdontologoRepository iOdontologoRepository;

    private ModelMapper modelMapper = new ModelMapper();


    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }

    public String addNewDentist(OdontologoDTO odontologoDTO){

        Odontologo odontologo = modelMapper.map(odontologoDTO, Odontologo.class);
        iOdontologoRepository.save(odontologo);
        return "El odontologo " + odontologo.getApellido() + " se creo con exito";
    }

    public String modifyDentist(OdontologoDTO odontologoDTO, Long id){
        Odontologo odontologo = modelMapper.map(odontologoDTO, Odontologo.class);
        odontologo.setId(id);
        iOdontologoRepository.save(odontologo);
        return "El odontologo " + odontologo.getApellido() + " se modifico con exito";
    }

    public OdontologoDTO getDentist(Long id){
        
        OdontologoDTO odontologoDTO = modelMapper.map((iOdontologoRepository.findById(id).get()), OdontologoDTO.class);
        return odontologoDTO;
    }

    public List<OdontologoDTO> getAllDentist(){
        List<OdontologoDTO> odontologoDTOList = iOdontologoRepository.findAll().stream().map(
                odontologo -> modelMapper.map(odontologo, OdontologoDTO.class))
                .collect(Collectors.toList());
        return odontologoDTOList;
    }

    public String deleteDentist(Long id){
        iOdontologoRepository.deleteById(id);
        return "El odontologo fue eliminado con exito";
    }
}
