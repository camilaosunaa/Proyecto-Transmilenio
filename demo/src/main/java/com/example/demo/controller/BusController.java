package com.example.demo.controller;

import com.example.demo.DTO.BusDTO;
import com.example.demo.init.inicializadorDB;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.service.ServiceBus;
import com.example.demo.service.ServiceConductor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bus")
public class BusController {
    @Autowired
    private ServiceBus serviceBus;

    @Secured({"Coordinador"})
    @GetMapping("/{idBus}")
    public ResponseEntity<BusDTO> RecuperaBus(@PathVariable Long idBus){
        BusDTO busDTO = serviceBus.getBUs(idBus);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type","application/json")
                .body(busDTO);
    }

    @Secured({"Coordinador", "AdminRutas"})
    @GetMapping
    public List<BusDTO> RecuperarBuses() {
        // Recuperamos todos los buses
        List<Bus> buses = serviceBus.recuperarTodosBuses();
        // Convertimos la lista de buses a una lista de DTOs
        List<BusDTO> busesDTO = buses.stream()
                .map(bus -> {
                    // Verificamos si el conductor está presente
                    Long conductorId = bus.getConductor() != null ? bus.getConductor().getId() : null;

                    // Mapear los datos a BusDTO
                    return new BusDTO(
                            bus.getId(),
                            bus.getPlaca(),
                            bus.getModelo(),
                            conductorId); // Asignamos el ID del conductor o null
                })
                .collect(Collectors.toList());

        // Retornamos la lista de BusDTO
        return busesDTO;
    }

    @Secured({"Coordinador"})
    @PostMapping
    public ResponseEntity<Bus> crearBus(@RequestBody BusDTO busDTO) {
        try {
            // Llamamos al servicio para crear el bus
            Bus nuevoBus = serviceBus.createBus(busDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBus);
        } catch (Exception e) {
            // Si hay un error, retornamos un 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Secured({"Coordinador"})
    @PutMapping("/{idBus}")
    public BusDTO actualizarBus(@PathVariable Long idBus, @RequestBody BusDTO busDTO){
        return serviceBus.UpdateBus(idBus,busDTO);
    }

    @Secured({"Coordinador"})
    @GetMapping("/conductor/{idconductor}")
    public List<BusDTO> busesPorIdConductor(@PathVariable Long idconductor){
        return serviceBus.recuperarBusPorIdConductor(idconductor);
    }

    @Secured({"Coordinador"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        try {
            serviceBus.deleteBus(id);
            return ResponseEntity.ok("Bus eliminado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

