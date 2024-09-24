package com.example.demo.repositories;

import com.example.demo.modelo.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioConductor extends JpaRepository<Conductor, Long> {
    List<Conductor> findAllById(Long id);
    List<Conductor> findAllByNombre(String nombre);
    List<Conductor> findAllByCedula(Long cedula);
    List<Conductor> findAllByTelefono(Long telefono);
    List<Conductor> findAllByDireccion(String direccion);

    //void deleteById(Long id);
}

