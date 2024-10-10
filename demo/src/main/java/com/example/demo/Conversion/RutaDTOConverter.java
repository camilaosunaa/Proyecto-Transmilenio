package com.example.demo.Conversion;

import com.example.demo.DTO.RutaDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Ruta;
import com.example.demo.service.ServiceBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RutaDTOConverter {


    @Autowired
    private ServiceBus serviceBus;

    public RutaDTO EntityToDTO(Ruta ruta) {
        Long busId = (ruta.getBus() != null) ? ruta.getBus().getId() : null;  // Verifica si bus es null antes de acceder a su id
        return new RutaDTO(ruta.getId(), ruta.getCodigo(), ruta.getEstaciones(), busId, ruta.getHorario());
    }

    public Ruta DTOToEntity(RutaDTO rutaDTO) {
        Bus bus = null;
        if (rutaDTO.getIdBus() != null && rutaDTO.getIdBus() != 0) {
            bus = serviceBus.findBusById(rutaDTO.getIdBus());
            if (bus == null) {
                throw new RuntimeException("Bus no encontrado con ID: " + rutaDTO.getIdBus());
            }
        }
        return new Ruta(rutaDTO.getId(), rutaDTO.getCodigo(), rutaDTO.getEstaciones(), bus, rutaDTO.getHorario());
    }


    public List<RutaDTO> entityToDTO(Optional<Ruta> rutas){
        // TODO Implementar
        return null;
    }
}
