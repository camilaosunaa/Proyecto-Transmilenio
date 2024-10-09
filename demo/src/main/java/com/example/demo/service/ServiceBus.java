package com.example.demo.service;

import com.example.demo.Conversion.BusDTOConverter;
import com.example.demo.DTO.BusDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.repositories.RepositorioBus;
import com.example.demo.repositories.RepositorioConductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceBus  {
    @Autowired
    private RepositorioBus RB;

    @Autowired
    private RepositorioConductor conductorRepository;

    @Autowired
    private BusDTOConverter busDTOConverter;

    public BusDTO getBUs(Long id){
        return busDTOConverter.EntityToDTO(findBusById(id));
    }

    public List<BusDTO> recuperarBusPorIdConductor(Long idConductor){
        List<Bus> buses = recuperarTodosBuses();
        List<BusDTO> busesDTO = new ArrayList<>();
        for(Bus b:buses){
            if(b.getConductor().getId() == idConductor){
                BusDTO busDTO = busDTOConverter.EntityToDTO(b);
                busesDTO.add(busDTO);
            }
        }
        return busesDTO;
    }

    public BusDTO createBus(BusDTO busDTO){
        Bus bus = busDTOConverter.DTOToEntity(busDTO);
        return busDTOConverter.EntityToDTO(RB.save(bus));
    }

    public BusDTO UpdateBus(Long idBus, BusDTO busDTO) {

        Bus bus = busDTOConverter.DTOToEntity(busDTO);
        bus.setId(idBus);
        return busDTOConverter.EntityToDTO(RB.save(bus));
    }

    public Bus findBusById(Long id) {
        return RB.findById(id).orElse(null);
    }

    public List<Bus> recuperarTodosBuses(){
        return RB.findAll();
    }
}
