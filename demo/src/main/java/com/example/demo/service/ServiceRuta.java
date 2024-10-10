package com.example.demo.service;

import com.example.demo.Conversion.RutaDTOConverter;
import com.example.demo.DTO.RutaDTO;
import com.example.demo.modelo.Ruta;
import com.example.demo.repositories.RepositorioRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Ruta createRuta(RutaDTO rutaDTO) {
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        return repositorioRuta.save(ruta);
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
        // Buscar la ruta existente en la base de datos
        Ruta rutaExistente = repositorioRuta.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "La ruta no fue encontrada"));

        // Verificar si la ruta ya tiene un bus asignado
        if (rutaExistente.getBus() != null && rutaDTO.getIdBus() != null) {
            // Si la ruta ya tiene un bus y se intenta asignar otro bus, lanzar excepción
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se puede editar una ruta que ya tiene un bus asignado.");
        }

        // Convertir el DTO a la entidad y actualizar los datos
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        ruta.setId(id);

        // Guardar la ruta actualizada y devolver el DTO resultante
        return rutaDTOConverter.EntityToDTO(repositorioRuta.save(ruta));
    }


    public Ruta findRutaById(Long id) {
        return repositorioRuta.findById(id).orElse(null);
    }

    // Método para borrar una ruta, asegurándose de que no tenga buses asignados

}
