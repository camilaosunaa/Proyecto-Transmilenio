package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "asignacion") // Ensuring the table name matches the expected name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use camelCase for variables

    @ManyToMany
    @JoinTable(
            name = "asignacion_conductores",
            joinColumns = @JoinColumn(name = "asignacion_id"),
            inverseJoinColumns = @JoinColumn(name = "conductores_id"))
    private List<Conductor> conductores;

    @ManyToMany
    @JoinTable(
            name = "asignacion_buses",
            joinColumns = @JoinColumn(name = "asignacion_id"),
            inverseJoinColumns = @JoinColumn(name = "buses_id"))
    private List<Bus> buses;

    @ManyToMany
    @JoinTable(
            name = "asignacion_horarios",
            joinColumns = @JoinColumn(name = "asignacion_id"),
            inverseJoinColumns = @JoinColumn(name = "horarios_id"))
    private List<Horario> horarios;
}