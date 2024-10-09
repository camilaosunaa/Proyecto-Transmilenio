package com.example.demo.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ConductorDTO {
    private Long id;
    private String nombre;
    private Long cedula;
    private Long telefono;
    private String direccion;
    private Long id_bus;
}
