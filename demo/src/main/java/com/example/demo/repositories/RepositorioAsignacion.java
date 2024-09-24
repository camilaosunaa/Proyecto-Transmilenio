package com.example.demo.repositories;

import com.example.demo.modelo.Asignacion;
import com.example.demo.modelo.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAsignacion extends JpaRepository<Asignacion,Long> {

}
