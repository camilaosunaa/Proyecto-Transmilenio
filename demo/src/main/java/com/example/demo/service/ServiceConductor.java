package com.example.demo.service;

import java.util.List;

import com.example.demo.Conversion.ConductorDTOConverter;
import com.example.demo.DTO.ConductorDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.repositories.RepositorioBus;
import com.example.demo.repositories.RepositorioConductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service

public class ServiceConductor {
    @Autowired
    private RepositorioConductor repositorioConductor;

    @Autowired
    private RepositorioBus busRepository;

    @Autowired
    private ConductorDTOConverter conductorDTOConverter;

    public ConductorDTO getConductor(Long id){
        return conductorDTOConverter.EntityToDTO(recuperarConductor(id));
    }

    public ConductorDTO createConductor(ConductorDTO conductorDTO){
        Conductor conductor = conductorDTOConverter.DTOToEntity(conductorDTO);
        return conductorDTOConverter.EntityToDTO(repositorioConductor.save(conductor));
    }

    public ConductorDTO UpdateConductor(Long id, ConductorDTO conductorDTO){
        Conductor conductor = conductorDTOConverter.DTOToEntity(conductorDTO);
        conductor.setId(id);
        return conductorDTOConverter.EntityToDTO(repositorioConductor.save(conductor));
    }


    public void eliminarConductor(Long id) {
        repositorioConductor.deleteById(id);
    }

    public List<Conductor> ListaConductor(){
        return repositorioConductor.findAll();
    }

    public Conductor recuperarConductor(Long id){
        return repositorioConductor.findById(id).orElseThrow();
    }

    public List<Conductor> recuperarTodoConductor(){return repositorioConductor.findAll();}
    public void guardarConductor(Conductor conductor){
        repositorioConductor.save(conductor);
    }

    public List<Conductor> buscarPorNombre(String nombre){
        return repositorioConductor.findAllByNombre(nombre);
    }

    // Otros métodos...

    public List<Bus> recuperarTodosBuses() {
        return busRepository.findAll();
    }


}
