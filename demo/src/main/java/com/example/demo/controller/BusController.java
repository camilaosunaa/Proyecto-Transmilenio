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
import org.springframework.stereotype.Controller;
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


    @GetMapping("/{idBus}")
    public ResponseEntity<BusDTO> RecuperaBus(@PathVariable Long idBus){
        BusDTO busDTO = serviceBus.getBUs(idBus);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type","application/json")
                .body(busDTO);
    }

    @GetMapping
    public List<BusDTO> RecuperarBuses(){
        List<Bus> buses = serviceBus.recuperarTodosBuses();

        // Convertir lista de buses a lista de DTOs
        List<BusDTO> busesDTO = buses.stream()
                .map(bus -> new BusDTO(
                        bus.getId(),
                        bus.getPlaca(),
                        bus.getModelo(),
                        bus.getConductor().getId()))
                .collect(Collectors.toList());
        return busesDTO;
    }

    @PostMapping
    public BusDTO CrearBuses(@RequestBody BusDTO busDTO){
        return serviceBus.createBus(busDTO);
    }

    @PutMapping("/{idBus}")
    public BusDTO actualizarBus(@PathVariable Long idBus, @RequestBody BusDTO busDTO){
        return serviceBus.UpdateBus(idBus,busDTO);
    }

}

