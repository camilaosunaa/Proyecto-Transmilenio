package com.example.demo.service;

import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Horario;
import com.example.demo.repositories.RepositorioHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceHorario {
    @Autowired
    private RepositorioHorario repositorioHorario;

    public List<Horario> recuperarTodosHorarios() {
        return repositorioHorario.findAll();
    }


    public String obtenerDiaHorario(Long id) {
        Horario horario = repositorioHorario.findById(id).orElse(null);
        return (horario != null) ? horario.getDia() : "sin asignar";
    }
}
