package com.example.demo.controller;

import com.example.demo.DTO.HorarioDTO;
import com.example.demo.modelo.Horario;
import com.example.demo.service.ServiceHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private ServiceHorario serviceHorario;

    @GetMapping("/{idhorario}")
    public ResponseEntity<HorarioDTO> RecuperarHorario(@PathVariable Long idhorario){
        HorarioDTO horarioDTO = serviceHorario.getHorario(idhorario);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type", "application/json")
                .body(horarioDTO);
    }

    @GetMapping
    public List<Horario> RecuperarHorarios(){
        return serviceHorario.recuperarTodosHorarios();
    }

    @PostMapping
    public HorarioDTO CrearHorario(@RequestBody HorarioDTO horarioDTO){
        return serviceHorario.createHorario(horarioDTO);
    }

    @PutMapping ("/{idhorario}")
    public HorarioDTO ActualizarHorario(@PathVariable Long idhorario, @RequestBody HorarioDTO horarioDTO){
        return serviceHorario.UpdateHorario(idhorario,horarioDTO);
    }
}
