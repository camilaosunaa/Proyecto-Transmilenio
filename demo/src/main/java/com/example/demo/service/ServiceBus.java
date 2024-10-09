package com.example.demo.service;

import com.example.demo.Conversion.BusDTOConverter;
import com.example.demo.DTO.BusDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.modelo.Horario;
import com.example.demo.modelo.Ruta;
import com.example.demo.repositories.RepositorioBus;
import com.example.demo.repositories.RepositorioConductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
