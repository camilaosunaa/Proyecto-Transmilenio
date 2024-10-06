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
        public List<Conductor> RecuperarConductores() {
            List<Conductor> conductores = serviceConductor.recuperarTodoConductor();
            return conductores;
        }

        @PostMapping
        public ConductorDTO CrearConductor(@RequestBody ConductorDTO conductorDTO){
            return serviceConductor.createConductor(conductorDTO);
        }

        @PutMapping("/{idConductor}")
        public ConductorDTO actualizarConductor(@PathVariable Long idConductor, @RequestBody ConductorDTO conductorDTO){
            return serviceConductor.UpdateConductor(idConductor,conductorDTO);
        }

    }
