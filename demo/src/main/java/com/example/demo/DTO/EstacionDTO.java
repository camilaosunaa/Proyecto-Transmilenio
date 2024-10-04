package com.example.demo.DTO;

import com.example.demo.modelo.Ruta;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class EstacionDTO {

    private Long id;

    private String nombre;

    private Long rutaId;
}
