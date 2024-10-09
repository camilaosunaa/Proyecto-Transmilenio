package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "conductor")
@Getter
@Setter
@NoArgsConstructor
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


    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Bus> buses;

    public Conductor(Long id, String nombre, Long cedula, Long telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
    }


}
