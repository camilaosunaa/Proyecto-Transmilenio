package com.example.demo.repositories;

import com.example.demo.modelo.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Bus; // Asegúrate de que esta ruta sea correcta

import java.util.List;
import java.util.Optional;


public interface RepositorioBus extends JpaRepository<Bus,Long> {

}
