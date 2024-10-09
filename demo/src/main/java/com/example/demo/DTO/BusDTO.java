package com.example.demo.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class BusDTO {
    private Long id;
    private String placa;
    private String modelo;
    private Long idConductor;
}
