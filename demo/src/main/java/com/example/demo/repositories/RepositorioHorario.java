package com.example.demo.repositories;

import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioHorario extends JpaRepository<Horario,Long> {

}
