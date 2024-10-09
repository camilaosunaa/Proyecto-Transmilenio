package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("idConductor")
    private Long idConductor;
}
