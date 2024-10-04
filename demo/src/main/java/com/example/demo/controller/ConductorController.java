package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.DTO.ConductorDTO;
import com.example.demo.init.inicializadorDB;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.modelo.Horario;
import com.example.demo.service.ServiceBus;
import com.example.demo.service.ServiceConductor;
import com.example.demo.service.ServiceHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/conductor")
public class ConductorController {

    @Autowired
    private ServiceConductor serviceConductor;

    @Autowired
    private ServiceBus serviceBus;

    @Autowired
    private ServiceHorario serviceHorario;

    @GetMapping("/{idconductor}")
    public ResponseEntity<ConductorDTO> RecuperarConductor(@PathVariable Long idconductor){
        ConductorDTO conductorDTO = serviceConductor.getConductor(idconductor);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-type", "application/json")
                .body(conductorDTO);
    }

    @GetMapping
    public List<Conductor> RecuperarConductores(){
        List<Conductor> conductores = serviceConductor.recuperarTodoConductor();
        return conductores;
    }

    @PostMapping
    public ConductorDTO CrearConductor(@RequestBody ConductorDTO conductorDTO){
        return serviceConductor.createConductor(conductorDTO);
    }

    @PutMapping("/{idconductor}")
    public ConductorDTO actualizarConductor(@PathVariable Long idConductor, @RequestBody ConductorDTO conductorDTO){
        return serviceConductor.UpdateConductor(idConductor,conductorDTO);
    }



    /*@GetMapping("/list")
    public ModelAndView listaConductores() {
        // Obtener la lista de conductores
        List<Conductor> conductores = serviceConductor.ListaConductor();

        // Crear el ModelAndView y añadir la lista de conductores
        ModelAndView modelAndView = new ModelAndView("Conductor-list");
        modelAndView.addObject("conductores", conductores);

        // Crear el mapa de placas de buses y añadirlo al ModelAndView
        modelAndView.addObject("busPlacas", obtenerBusPlacas(conductores));
        modelAndView.addObject("diaHorario", obtenerHorarios(conductores));
        return modelAndView;
    }

    private Map<Long, String> obtenerBusPlacas(List<Conductor> conductores) {
        Map<Long, String> busPlacas = new HashMap<>();
        for (Conductor conductor : conductores) {
            if (conductor.getId_bus() != null) {
                String placa = serviceBus.obtenerPlacaBus(conductor.getId_bus());
                busPlacas.put(conductor.getId_bus(), placa);
            }

        }
        return busPlacas;
    }

    private Map<Long, String> obtenerHorarios(List<Conductor> conductores) {
        Map<Long, String> horarios = new HashMap<>();
        for (Conductor conductor : conductores) {
            if (conductor.getId_horario() != null) {
                String dia = serviceHorario.obtenerDiaHorario(conductor.getId_horario());
                horarios.put(conductor.getId_horario(), dia);
            }
        }
        return horarios;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditar(@PathVariable Long id) {
        Conductor conductor = serviceConductor.recuperarConductor(id);
        List<Bus> buses = serviceConductor.recuperarTodosBuses();
        List<Horario> horarios= serviceHorario.recuperarTodosHorarios();
        ModelAndView modelAndView = new ModelAndView("Conductor_edit");
        modelAndView.addObject("conductor", conductor);
        modelAndView.addObject("buses", buses);
        modelAndView.addObject("horarios",horarios);
        return modelAndView;
    }

    @PostMapping("/save")
    public Object guardarConductor(@Valid @ModelAttribute Conductor conductor, BindingResult result) {
        if (result.hasErrors()) {
            List<Bus> buses = serviceBus.recuperarTodosBuses();
            List<Horario> horarios = serviceHorario.recuperarTodosHorarios();
            return new ModelAndView("Conductor_edit")
                    .addObject("conductor", conductor)
                    .addObject("buses", buses)
                    .addObject("horarios", horarios);
        }

        // Verificar que se haya asignado un bus y un horario
        if (conductor.getId_bus() == null || conductor.getId_horario() == null) {
            List<Bus> buses = serviceBus.recuperarTodosBuses();
            List<Horario> horarios = serviceHorario.recuperarTodosHorarios();
            return new ModelAndView("Conductor_edit")
                    .addObject("conductor", conductor)
                    .addObject("buses", buses)
                    .addObject("horarios", horarios)
                    .addObject("message", "Debe asignar un bus y un horario al conductor.");
        }

        // Guardar o actualizar el conductor
        serviceConductor.guardarConductor(conductor);

        // Redirigir a la lista de conductores después de guardar
        return new RedirectView("/conductor/list");
    }


    @GetMapping("/add-form")
    public ModelAndView formularioAgregar() {
        Conductor nuevoConductor = new Conductor();
        List<Bus> buses = serviceConductor.recuperarTodosBuses();
        List<Horario> horarios = serviceHorario.recuperarTodosHorarios();
        ModelAndView modelAndView = new ModelAndView("Conductor_add");
        modelAndView.addObject("conductor", nuevoConductor);
        modelAndView.addObject("buses", buses);
        modelAndView.addObject("horarios", horarios);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public Object eliminarConductor(@PathVariable Long id) {
        try {
            serviceConductor.eliminarConductor(id);
        } catch (Exception e) {
            return new ModelAndView("error", "message", "No se pudo eliminar el conductor. Puede que no exista.");
        }
        return new RedirectView("/conductor/list");
    }*/
}
