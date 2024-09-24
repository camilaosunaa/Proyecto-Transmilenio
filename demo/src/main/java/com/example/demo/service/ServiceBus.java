package com.example.demo.service;

import com.example.demo.modelo.Bus;
import com.example.demo.repositories.RepositorioBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBus  {
    @Autowired
    private RepositorioBus RB;

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
