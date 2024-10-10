package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Usar camelCase para las variables

    @Column(name = "placa", nullable = false)
    private String placa; // Usar camelCase para las variables

    @Column(name = "modelo", nullable = false)
    private String modelo; // Usar camelCase para las variables


    @OneToMany(mappedBy = "bus")
    @JsonIgnore
    private List<Ruta> rutas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conductor", nullable = true)
    private Conductor conductor;

    public Bus(Long id, String placa, String modelo,Conductor conductor) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.conductor = conductor;
    }
}