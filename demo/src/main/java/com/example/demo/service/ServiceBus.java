package com.example.demo.service;

import com.example.demo.Conversion.BusDTOConverter;
import com.example.demo.DTO.BusDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Horario;
import com.example.demo.repositories.RepositorioBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBus  {
    @Autowired
    private RepositorioBus RB;

    @Autowired
    private BusDTOConverter busDTOConverter;

    public BusDTO getBUs(Long id){
        return busDTOConverter.EntityToDTO(obtenerBusPorId(id));
    }

    public void deleteBus(Long id){
        Bus bus = RB.findById(id).orElseThrow(() -> new RuntimeException("Conductor no encontrado con id: " + id));
        RB.delete(bus);
    }

    public BusDTO createBus(BusDTO busDTO){
        Bus bus = busDTOConverter.DTOToEntity(busDTO);
        return busDTOConverter.EntityToDTO(RB.save(bus));
    }

    public BusDTO UpdateBus(Long idBUs, BusDTO busDTO){
        Bus bus = busDTOConverter.DTOToEntity(busDTO);
        bus.setId(idBUs);
        return busDTOConverter.EntityToDTO(RB.save(bus));
    }

    public String obtenerPlacaBus(Long idBus) {
        Bus bus = RB.findById(idBus).orElse(null);
        return (bus != null) ? bus.getPlaca() : "Sin asignar";
    }

    public void eliminarBus(Long id){
        RB.deleteById(id);
    }
    public List<Bus> ListaBuses(){
        return RB.findAll();
    }
    public List<Bus> recuperarTodosBuses() {
        return RB.findAll();
    }

    public Bus obtenerBusPorId(Long id) {
        Optional<Bus> bus = RB.findById(id);
        if (bus.isPresent()) {
            return bus.get();
        } else {
            throw new RuntimeException("Bus no encontrado con ID: " + id);
        }
    }
    public Bus recuperarbus(Long id){return RB.findById(id).orElseThrow();}

    public void guardarBus(Bus bus){RB.save(bus);}

}
