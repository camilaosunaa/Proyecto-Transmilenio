package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "horario") // Ensuring the table name matches the expected name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use camelCase for variables

    @Column(name = "dia", nullable = false)
    private String dia; // Use camelCase for variables

    @Column(name = "hora_inicio", nullable = false )
    private LocalTime horaInicio;

    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;
}