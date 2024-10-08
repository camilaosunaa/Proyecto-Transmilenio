package com.example.demo.Conversion;

import com.example.demo.DTO.BusDTO;
import com.example.demo.modelo.Bus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BusDTOConverter {
    public BusDTO EntityToDTO(Bus bus){
        return new BusDTO(bus.getId(), bus.getPlaca(), bus.getModelo(), bus.getIdRuta());
    }

    public Bus DTOToEntity(BusDTO busDTO){
        return new Bus(busDTO.getId(), busDTO.getPlaca(), busDTO.getModelo(), busDTO.getIdRuta());
    }

    public List<BusDTO> entityToDTO(Optional<Bus> buses){
        // TODO Implementar
        return null;
    }
}
