package com.example.demo.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "conductor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", nullable = false)
    @NotBlank(message = "No puede estar en blanco")
    private String nombre;

    @Column(name = "Cedula", nullable = false)
    @NotNull(message = "No puede estar en blanco")
    private Long cedula;

    @Column(name = "Telefono", nullable = false)
    @NotNull(message = "No puede estar en blanco")
    private Long telefono;

    @Column(name = "Direccion", nullable = false)
    @NotBlank(message = "No puede estar en blanco")
    private String direccion;

    @Column(name = "bus_id", nullable = true)
    private Long id_bus;

    @Column(name = "id_horario", nullable = false)
    private Long id_horario;
}
