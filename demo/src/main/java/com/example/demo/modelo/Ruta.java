package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ruta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ElementCollection
    @CollectionTable(name = "ruta_estaciones", joinColumns = @JoinColumn(name = "ruta_id"))
    @Column(name = "estacion")
    private List<String> estaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbus",referencedColumnName = "id", nullable = true)
    private Bus bus;

    @Column(name = "horario", nullable = true)
    private String horario;
}

