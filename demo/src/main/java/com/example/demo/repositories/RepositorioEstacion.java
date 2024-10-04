package com.example.demo.repositories;

import com.example.demo.modelo.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEstacion extends JpaRepository<Estacion,Long> {
}
