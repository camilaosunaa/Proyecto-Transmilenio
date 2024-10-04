package com.example.demo.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class HorarioDTO {
    private Long id;
    private int dia; // Use camelCase for variables
    private int mes;
    private int anho;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
}
