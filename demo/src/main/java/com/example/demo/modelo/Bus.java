package com.example.demo.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "bus") // Ensuring the table name matches the expected name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use camelCase for variables

    @Column(name = "placa", nullable = false)
    private String placa; // Use camelCase for variables

    @Column(name = "modelo", nullable = false)
    private String modelo; // Use camelCase for variables
}