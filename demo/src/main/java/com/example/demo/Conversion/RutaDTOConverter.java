package com.example.demo.Conversion;

import com.example.demo.DTO.RutaDTO;
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

    public RutaDTO EntityToDTO(Ruta ruta) {
        return new RutaDTO(ruta.getId(), ruta.getCodigo(), ruta.getEstaciones(), ruta.getIdhorario());
    }

    // Convierte un DTO RutaDTO a una entidad Ruta
    public Ruta DTOToEntity(RutaDTO rutaDTO) {
        return new Ruta(rutaDTO.getId(), rutaDTO.getCodigo(), rutaDTO.getEstaciones(), rutaDTO.getIdhorario());
    }

    public List<RutaDTO> entityToDTO(Optional<Ruta> rutas){
        // TODO Implementar
        return null;
    }
}
