package com.example.demo.controller;

import com.example.demo.DTO.RutaDTO;
import com.example.demo.modelo.Ruta;
import com.example.demo.service.ServiceRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private ServiceRuta serviceRuta;

    @GetMapping("/{idruta}")
    public ResponseEntity<RutaDTO> RecuperarRuta(@PathVariable Long idruta){
        RutaDTO rutaDTO = serviceRuta.getRuta(idruta);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type", "application/json")
                .body(rutaDTO);
    }

    @GetMapping
    public List<RutaDTO> RecuperarRutas(){
        List<Ruta> rutas = serviceRuta.RecuperarTodaRuta();
        List<RutaDTO> rutasDTO = rutas.stream()
                .map(ruta -> new RutaDTO(
                        ruta.getId(),
                        ruta.getCodigo(),
                        ruta.getEstaciones(),
                        ruta.getBus().getId(),
                        ruta.getHorario().getId()))
                .collect(Collectors.toList());

        return rutasDTO;
    }

    @PostMapping
    public RutaDTO CrearRuta(@RequestBody RutaDTO rutaDTO){
        return serviceRuta.createRuta(rutaDTO);
    }

    @PutMapping("/{idruta}")
    public RutaDTO ActualizarRuta(@PathVariable Long idruta, @RequestBody RutaDTO rutaDTO){
        return serviceRuta.updateRuta(idruta,rutaDTO);
    }

}
