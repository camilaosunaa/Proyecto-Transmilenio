package com.example.demo.Conversion;

import com.example.demo.DTO.BusDTO;
import com.example.demo.DTO.EstacionDTO;
import com.example.demo.DTO.RutaDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Estacion;
import com.example.demo.modelo.Ruta;
import com.example.demo.repositories.RepositorioRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RutaDTOConverter {

    @Autowired
    private EstacionDTOConverter estacionDTOConverter;
    public RutaDTO EntityToDTO(Ruta ruta) {
        List<EstacionDTO> estacionesDTO = ruta.getEstaciones().stream()
                .map(estacionDTOConverter::EntityToDTO)
                .collect(Collectors.toList());

        return new RutaDTO(ruta.getId(), ruta.getCodigo(), estacionesDTO);
    }
    public Ruta DTOToEntity(RutaDTO rutaDTO, RepositorioRuta repositorioRuta) {
        List<Estacion> estaciones = rutaDTO.getEstaciones().stream()
                .map(estacionDTO -> estacionDTOConverter.DTOToEntity(estacionDTO, repositorioRuta)) // Convierte EstacionDTO a Estacion
                .collect(Collectors.toList());

        return new Ruta(rutaDTO.getId(), rutaDTO.getCodigo(), estaciones);
    }

    public List<RutaDTO> entityToDTO(Optional<Ruta> rutas){
        // TODO Implementar
        return null;
    }
}
