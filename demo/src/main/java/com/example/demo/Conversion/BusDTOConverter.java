package com.example.demo.Conversion;

import com.example.demo.DTO.BusDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.modelo.Ruta;
import com.example.demo.service.ServiceConductor;
import com.example.demo.service.ServiceRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class BusDTOConverter {

    @Autowired
    private ServiceConductor serviceConductor;
    public BusDTO EntityToDTO(Bus bus){
        return new BusDTO(bus.getId(), bus.getPlaca(), bus.getModelo(), bus.getConductor().getId());
    }

    public Bus DTOToEntity(BusDTO busDTO){
        Conductor conductor = serviceConductor.recuperarConductor(busDTO.getIdConductor());
        if (conductor == null) {
            throw new RuntimeException("Ruta no encontrada con ID: " + busDTO.getIdConductor());
        }
        return new Bus(busDTO.getId(), busDTO.getPlaca(), busDTO.getModelo(), conductor);
    }

    public List<BusDTO> entityToDTO(Optional<Bus> buses){
        // TODO Implementar
        return null;
    }
}
