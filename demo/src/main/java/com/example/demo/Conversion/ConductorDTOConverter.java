package com.example.demo.Conversion;

import com.example.demo.DTO.ConductorDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.service.ServiceBus;
import org.hibernate.annotations.CollectionTypeRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConductorDTOConverter {

    public ConductorDTO EntityToDTO(Conductor conductor){
        return new ConductorDTO(conductor.getId(), conductor.getNombre(), conductor.getCedula(), conductor.getTelefono(), conductor.getDireccion());
    }

    public Conductor DTOToEntity(ConductorDTO conductorDTO){
        return new Conductor(conductorDTO.getId(), conductorDTO.getNombre(), conductorDTO.getCedula(), conductorDTO.getTelefono(), conductorDTO.getDireccion());
    }

    public List<ConductorDTO> entityToDT(List<Conductor> conductores){
        //TODO implementar
        return null;
    }
}
