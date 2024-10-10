package com.example.demo.service;

import com.example.demo.Conversion.RutaDTOConverter;
import com.example.demo.DTO.RutaDTO;
import com.example.demo.modelo.Ruta;
import com.example.demo.repositories.RepositorioRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRuta {
    @Autowired
    private RepositorioRuta repositorioRuta;

    @Autowired
    private RutaDTOConverter rutaDTOConverter;

    public List<Ruta> RecuperarTodaRuta() {
        return repositorioRuta.findAll();
    }

    public Ruta RecuperarRuta(Long id) {
        return repositorioRuta.findById(id).orElseThrow();
    }

    public void deleteRuta(Long id) {
        Ruta ruta = findRutaById(id);
        if (ruta == null) {
            throw new RuntimeException("Ruta no encontrada con ID: " + id);
        }

        if (ruta.getBus() != null) {
            throw new RuntimeException("La ruta no puede ser eliminada porque tiene un bus asignado.");
        }

        repositorioRuta.delete(ruta);
    }

    public RutaDTO getRuta(Long id) {
        return rutaDTOConverter.EntityToDTO(RecuperarRuta(id));
    }

    public RutaDTO createRuta(RutaDTO rutaDTO) {
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        return rutaDTOConverter.EntityToDTO(repositorioRuta.save(ruta));
    }

    public List<RutaDTO> buscarRutaPorIdBus(Long idbus) {
        List<Ruta> rutas = RecuperarTodaRuta();
        List<RutaDTO> rutasDTO = new ArrayList<>();

        for (Ruta r : rutas) {
            if (r.getBus() != null && r.getBus().getId() == idbus) {
                RutaDTO rutaDTO = rutaDTOConverter.EntityToDTO(r);
                rutasDTO.add(rutaDTO);
            }
        }
        return rutasDTO;
    }

    public RutaDTO updateRuta(Long id, RutaDTO rutaDTO) {
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        ruta.setId(id);

        // Permitir que el bus sea null si no está asignado
        return rutaDTOConverter.EntityToDTO(repositorioRuta.save(ruta));
    }


    public Ruta findRutaById(Long id) {
        return repositorioRuta.findById(id).orElse(null);
    }

    // Método para borrar una ruta, asegurándose de que no tenga buses asignados

}
