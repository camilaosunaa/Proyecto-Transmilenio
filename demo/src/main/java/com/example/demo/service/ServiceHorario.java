package com.example.demo.service;

import com.example.demo.Conversion.HorarioDTOConverter;
import com.example.demo.DTO.HorarioDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Horario;
import com.example.demo.repositories.RepositorioHorario;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceHorario {
    @Autowired
    private RepositorioHorario repositorioHorario;

    @Autowired
    private HorarioDTOConverter horarioDTOConverter;

    public Horario recuperarHorario(Long id){return repositorioHorario.findById(id).orElseThrow();}
    public List<Horario> recuperarTodosHorarios() {
        return repositorioHorario.findAll();
    }

    public HorarioDTO getHorario(Long id){return horarioDTOConverter.EntityToDTO(recuperarHorario(id));}

    public HorarioDTO createHorario(HorarioDTO horarioDTO){
        Horario horario = horarioDTOConverter.DTOToEntity(horarioDTO);
        return horarioDTOConverter.EntityToDTO(repositorioHorario.save(horario));
    }

    public HorarioDTO UpdateHorario(Long id, HorarioDTO horarioDTO){
        Horario horario = horarioDTOConverter.DTOToEntity(horarioDTO);
        horario.setId(id);
        return horarioDTOConverter.EntityToDTO(repositorioHorario.save(horario));
    }

}
