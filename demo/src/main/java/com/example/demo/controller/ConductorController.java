    package com.example.demo.controller;

    import java.util.List;
    import java.util.stream.Collectors;

    import com.example.demo.DTO.ConductorDTO;
    import com.example.demo.modelo.Conductor;
    import com.example.demo.service.ServiceConductor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/conductor")
    public class ConductorController {

        @Autowired
        private ServiceConductor serviceConductor;

        @GetMapping("/{idconductor}")
        public ResponseEntity<ConductorDTO> RecuperarConductor(@PathVariable Long idconductor){
            ConductorDTO conductorDTO = serviceConductor.getConductor(idconductor);
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-type", "application/json")
                    .body(conductorDTO);
        }

        @GetMapping
        public List<ConductorDTO> RecuperarConductores() {
            List<Conductor> conductores = serviceConductor.recuperarTodoConductor();

            // Convertir lista de conductores a lista de DTOs
            List<ConductorDTO> conductoresDTO = conductores.stream()
                    .map(conductor -> new ConductorDTO(
                            conductor.getId(),
                            conductor.getNombre(),
                            conductor.getCedula(),
                            conductor.getTelefono(),
                            conductor.getDireccion()))
                    .collect(Collectors.toList());

            return conductoresDTO;
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
