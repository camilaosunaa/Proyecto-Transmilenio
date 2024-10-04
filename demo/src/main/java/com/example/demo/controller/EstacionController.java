package com.example.demo.controller;

import com.example.demo.DTO.ConductorDTO;
import com.example.demo.DTO.EstacionDTO;
import com.example.demo.modelo.Estacion;
import com.example.demo.service.ServiceEstacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacion")
public class EstacionController {
    @Autowired
    private ServiceEstacion serviceEstacion;

    @GetMapping("/{idestacion}")
    public ResponseEntity<EstacionDTO> RecuperarEstacion(@PathVariable Long idestacion){
        EstacionDTO estacionDTO = serviceEstacion.getEstacion(idestacion);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type", "application/json")
                .body(estacionDTO);
    }

    @GetMapping
    public List<EstacionDTO> RecuperarEstaciones(){
        return serviceEstacion.RecuperarTodaEstacion();
    }

    @PostMapping
    public EstacionDTO CrearEstacion(@RequestBody EstacionDTO estacionDTO){
        return serviceEstacion.createEstacion(estacionDTO);
    }

    @PutMapping("/{idestacion}")
    public EstacionDTO ActualizarEstacion(@PathVariable Long idestacion, @RequestBody EstacionDTO estacionDTO){
        return serviceEstacion.updateEstacion(idestacion,estacionDTO);
    }


}
