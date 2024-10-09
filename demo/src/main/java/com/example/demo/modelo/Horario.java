package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use camelCase for variables

    @Column(name = "diasemana", nullable = false)
    private  String diaSemana;

    @Column(name = "dia", nullable = false)
    private int dia; // Use camelCase for variables

    @Column(name="mes", nullable = false)
    private int mes;

    @Column(name = "año", nullable = false)
    private int anho;

    @Column(name = "hora_inicio", nullable = false )
    private LocalTime horaInicio;

    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;

    @JsonIgnore
    @OneToOne(mappedBy = "horario")
    private Ruta ruta;

    public Horario(Long id, String diaSemana, int dia, int mes, int anho, LocalTime horaInicio, LocalTime horaFinal) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.dia = dia;
        this.mes = mes;
        this.anho = anho;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
}