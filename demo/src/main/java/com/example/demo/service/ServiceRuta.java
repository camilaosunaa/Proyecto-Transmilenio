package com.example.demo.service;

import com.example.demo.Conversion.RutaDTOConverter;
import com.example.demo.DTO.RutaDTO;
import com.example.demo.modelo.Ruta;
import com.example.demo.repositories.RepositorioRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRuta {
    @Autowired
    private RepositorioRuta repositorioRuta;

    @Autowired
    private RutaDTOConverter rutaDTOConverter;

    public List<RutaDTO> RecuperarTodaRuta() {
        List<Ruta> rutas = repositorioRuta.findAll();
        return rutas.stream()
                .map(rutaDTOConverter::EntityToDTO)
                .collect(Collectors.toList());
    }
    public Ruta RecuperarRuta(Long id){return repositorioRuta.findById(id).orElseThrow();}

    public RutaDTO getRuta(Long id){return rutaDTOConverter.EntityToDTO(RecuperarRuta(id));}

    public RutaDTO createRuta(RutaDTO rutaDTO){
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        return rutaDTOConverter.EntityToDTO(repositorioRuta.save(ruta));
    }

    public RutaDTO updateRuta(Long id, RutaDTO rutaDTO){
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        ruta.setId(id);
        return rutaDTOConverter.EntityToDTO(repositorioRuta.save(ruta));
    }
}
