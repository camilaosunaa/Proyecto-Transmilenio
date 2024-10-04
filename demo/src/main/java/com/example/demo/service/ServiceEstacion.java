package com.example.demo.service;

import com.example.demo.Conversion.EstacionDTOConverter;
import com.example.demo.DTO.EstacionDTO;
import com.example.demo.modelo.Estacion;
import com.example.demo.repositories.RepositorioEstacion;
import com.example.demo.repositories.RepositorioRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceEstacion {
    @Autowired
    private RepositorioEstacion repositorioEstacion;

    @Autowired
    private RepositorioRuta repositorioRuta;

    @Autowired
    private EstacionDTOConverter estacionDTOConverter;

    public Estacion RecuperarEstacion(Long id){return repositorioEstacion.findById(id).orElseThrow();}

    public List<EstacionDTO> RecuperarTodaEstacion() {
        List<Estacion> estaciones = repositorioEstacion.findAll();
        return estaciones.stream()
                .map(estacionDTOConverter::EntityToDTO)
                .collect(Collectors.toList());
    }

    public EstacionDTO getEstacion(Long id){return estacionDTOConverter.EntityToDTO(RecuperarEstacion(id));}

    public EstacionDTO createEstacion(EstacionDTO estacionDTO){
        Estacion estacion = estacionDTOConverter.DTOToEntity(estacionDTO,repositorioRuta);
        return estacionDTOConverter.EntityToDTO(repositorioEstacion.save(estacion));
    }

    public EstacionDTO updateEstacion(Long id, EstacionDTO estacionDTO){
        Estacion estacion = estacionDTOConverter.DTOToEntity(estacionDTO,repositorioRuta);
        estacion.setId(id);
        return estacionDTOConverter.EntityToDTO(repositorioEstacion.save(estacion));
    }
}
