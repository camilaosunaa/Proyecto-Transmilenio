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
    public static BusDTO EntityToDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();

        // Verificamos si el conductor es null
        if (bus.getConductor() != null) {
            busDTO.setIdConductor(bus.getConductor().getId());
        } else {
            busDTO.setIdConductor(null);  // Manejo del caso cuando no hay conductor
        }

        // Continúa con el resto del mapeo de propiedades
        busDTO.setId(bus.getId());
        busDTO.setPlaca(bus.getPlaca());
        busDTO.setModelo(bus.getModelo());

        return busDTO;
    }

    public Bus DTOToEntity(BusDTO busDTO){
        Conductor conductor = null;
        if (busDTO.getIdConductor() != null) {
            conductor = serviceConductor.recuperarConductor(busDTO.getIdConductor());
            if (conductor == null) {
                throw new RuntimeException("Conductor no encontrado con ID: " + busDTO.getIdConductor());
            }
        }
        return new Bus(busDTO.getId(), busDTO.getPlaca(), busDTO.getModelo(), conductor);
    }

    public List<BusDTO> entityToDTO(Optional<Bus> buses){
        // TODO Implementar
        return null;
    }
}
