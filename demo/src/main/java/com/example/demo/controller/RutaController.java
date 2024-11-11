package com.example.demo.controller;

import com.example.demo.DTO.RutaDTO;
import com.example.demo.modelo.Ruta;
import com.example.demo.service.ServiceRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruta")
public class
RutaController {
    @Autowired
    private ServiceRuta serviceRuta;

    @Secured({ "AdminRutas","Cliente"})
    @GetMapping("/{idruta}")
    public ResponseEntity<RutaDTO> RecuperarRuta(@PathVariable Long idruta){
        RutaDTO rutaDTO = serviceRuta.getRuta(idruta);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type", "application/json")
                .body(rutaDTO);
    }

    @Secured({ "AdminRutas" , "Cliente"})
    @GetMapping("/list")
    public List<RutaDTO> RecuperarRutas() {
        return serviceRuta.RecuperarTodaRuta().stream()
                .map(ruta -> {
                    RutaDTO dto = new RutaDTO();
                    dto.setId(ruta.getId());
                    dto.setCodigo(ruta.getCodigo());
                    dto.setHorario(ruta.getHorario());

                    // Verificamos si el Bus no es nulo antes de acceder a su ID
                    if (ruta.getBus() != null) {
                        dto.setIdBus(ruta.getBus().getId());
                    } else {
                        dto.setIdBus(null);
                    }

                    // Directamente asignamos la lista de estaciones de la ruta
                    dto.setEstaciones(ruta.getEstaciones());

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Secured({ "AdminRutas" })
    @PostMapping
    public ResponseEntity<Ruta> CrearRuta(@RequestBody RutaDTO rutaDTO){
        try{
            Ruta ruta = serviceRuta.createRuta(rutaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(ruta);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Secured({ "AdminRutas" })
    @PutMapping("/{idruta}")
    public RutaDTO ActualizarRuta(@PathVariable Long idruta, @RequestBody RutaDTO rutaDTO){
        return serviceRuta.updateRuta(idruta,rutaDTO);
    }

    @Secured({ "AdminRutas", "Coordinador" })
    @GetMapping("/bus/{idbus}")
    public List<RutaDTO> recuperarRutasPorIdbus(@PathVariable Long idbus){
        return serviceRuta.buscarRutaPorIdBus(idbus);
    }

    @Secured({ "AdminRutas" })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRuta(@PathVariable Long id) {
        try {
            serviceRuta.deleteRuta(id);
            return ResponseEntity.ok("Ruta eliminada con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage()); // Error si no se puede eliminar
        }
    }

}
