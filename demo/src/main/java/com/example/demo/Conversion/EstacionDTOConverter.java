package com.example.demo.Conversion;

import com.example.demo.DTO.EstacionDTO;
import com.example.demo.modelo.Estacion;
import com.example.demo.modelo.Ruta;
import com.example.demo.repositories.RepositorioRuta;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EstacionDTOConverter {

    public EstacionDTO EntityToDTO(Estacion estacion){
        return new EstacionDTO(estacion.getId(), estacion.getNombre(), estacion.getRuta().getId());
    }

    public Estacion DTOToEntity(EstacionDTO estacionDTO, RepositorioRuta rutaRepository) {

        Ruta ruta = rutaRepository.findById(estacionDTO.getRutaId())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        return new Estacion(estacionDTO.getId(), estacionDTO.getNombre(), ruta);
    }

    public List<EstacionDTO> entityToDTO(Optional<Estacion> estacionOptional){
        return estacionOptional.map(estacion -> {
            EstacionDTO estacionDTO = new EstacionDTO(estacion.getId(), estacion.getNombre(), estacion.getRuta().getId());
            // Retorna una lista con un solo EstacionDTO
            return List.of(estacionDTO);
        }).orElseGet(() -> List.of());
    }
}
