package com.example.demo.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class RutaDTO {
    private Long id;
    private String codigo;
    private List<String> estaciones;
    private Long idBus;
    private String horario;
}
