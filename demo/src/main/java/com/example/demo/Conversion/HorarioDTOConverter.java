package com.example.demo.Conversion;

import com.example.demo.DTO.EstacionDTO;
import com.example.demo.DTO.HorarioDTO;
import com.example.demo.modelo.Estacion;
import com.example.demo.modelo.Horario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HorarioDTOConverter {
    public HorarioDTO EntityToDTO(Horario horario){
        return new HorarioDTO(horario.getId(), horario.getDia(),horario.getMes(),horario.getAnho(),horario.getHoraInicio(),horario.getHoraFinal());
    }

    public Horario DTOToEntity(HorarioDTO horarioDTO){
        return new Horario(horarioDTO.getId(), horarioDTO.getDia(),horarioDTO.getMes(),horarioDTO.getAnho(),horarioDTO.getHoraInicio(),horarioDTO.getHoraFinal());
    }

    public List<Horario> entityToDTO(Optional<HorarioDTO> horarioDTO){
        // TODO Implementar
        return null;
    }
}
