package com.example.demo.init;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.modelo.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class inicializadorDB implements CommandLineRunner {
    @Autowired
    private RepositorioConductor repositorioConductor;
    @Autowired
    private RepositorioBus repositorioBus;
    @Autowired
    private RepositorioAsignacion repositorioAsignacion;
    @Autowired
    private RepositorioHorario repositorioHorario;

    @Autowired
    private RepositorioRuta repositorioRuta;

    @Autowired
    private RepositorioEstacion repositorioEstacion;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {

        // Elimina las tablas existentes para evitar problemas de integridad referencial
        jdbcTemplate.execute("DROP TABLE IF EXISTS asignacion CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS conductor CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS bus CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS horario CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS estacion CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS ruta CASCADE");

        // Crea las tablas necesarias antes de insertar datos
        jdbcTemplate.execute("CREATE TABLE horario (id BIGINT AUTO_INCREMENT PRIMARY KEY, dia INT NOT NULL,mes INT NOT NULL,año INT NOT NULL, hora_inicio TIME NOT NULL, hora_final TIME NOT NULL)");
        jdbcTemplate.execute("CREATE TABLE bus (id BIGINT AUTO_INCREMENT PRIMARY KEY, placa VARCHAR(255) NOT NULL, modelo VARCHAR(255) NOT NULL, ruta_id BIGINT NOT NULL)");
        jdbcTemplate.execute("CREATE TABLE conductor (id BIGINT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255) NOT NULL, cedula BIGINT NOT NULL, telefono BIGINT NOT NULL, direccion VARCHAR(255) NOT NULL, bus_id BIGINT NOT NULL, id_horario BIGINT NOT NULL)");
        jdbcTemplate.execute("CREATE TABLE asignacion (id BIGINT AUTO_INCREMENT PRIMARY KEY)");
        jdbcTemplate.execute("CREATE TABLE ruta (id BIGINT AUTO_INCREMENT PRIMARY KEY, codigo VARCHAR(255) NOT NULL)");
        jdbcTemplate.execute("CREATE TABLE estacion (id BIGINT AUTO_INCREMENT PRIMARY KEY,nombre VARCHAR(255) NOT NULL, ruta_id BIGINT NOT NULL,FOREIGN KEY (ruta_id) REFERENCES ruta(id) ON DELETE CASCADE)");
        // Crea y guarda los horarios
        Horario horario1 = new Horario(null, 14,10,2024, LocalTime.of(8, 0), LocalTime.of(16, 0));
        Horario horario2 = new Horario(null, 10,10,2024, LocalTime.of(9, 0), LocalTime.of(17, 0));
        Horario horario3 = new Horario(null, 24,10,2024, LocalTime.of(10, 0), LocalTime.of(18, 0));
        Horario horario4 = new Horario(null, 31,10,2024, LocalTime.of(8, 30), LocalTime.of(16, 30));
        Horario horario5 = new Horario(null, 20,10,2024, LocalTime.of(7, 0), LocalTime.of(15, 0));

        horario1 = repositorioHorario.save(horario1);
        horario2 = repositorioHorario.save(horario2);
        horario3 = repositorioHorario.save(horario3);
        horario4 = repositorioHorario.save(horario4);
        horario5 = repositorioHorario.save(horario5);

        Ruta ruta1 = new Ruta(null, "RUTA-01", new ArrayList<>());
        Ruta ruta2 = new Ruta(null, "RUTA-02", new ArrayList<>());
        ruta1 = repositorioRuta.save(ruta1);
        ruta2 = repositorioRuta.save(ruta2);

        List<Estacion> estacionesRuta1 = List.of(
                new Estacion(null, "Estación A", ruta1),
                new Estacion(null, "Estación B", ruta1),
                new Estacion(null, "Estación C", ruta1)
        );

        List<Estacion> estacionesRuta2 = List.of(
                new Estacion(null, "Estación X", ruta2),
                new Estacion(null, "Estación Y", ruta2),
                new Estacion(null, "Estación Z", ruta2)
        );

        repositorioEstacion.saveAll(estacionesRuta1);
        repositorioEstacion.saveAll(estacionesRuta2);

        // Actualiza las rutas con las estaciones
        ruta1.setEstaciones(estacionesRuta1);
        ruta2.setEstaciones(estacionesRuta2);

        repositorioRuta.save(ruta1);
        repositorioRuta.save(ruta2);

        // Crea y guarda los buses
        Bus bus1 = new Bus(null, "ABC123", "Modelo1", ruta1.getId());
        Bus bus2 = new Bus(null, "DEF456", "Modelo2", ruta2.getId());
        bus1 = repositorioBus.save(bus1);
        bus2 = repositorioBus.save(bus2);

        // Crea y guarda los conductores
        List<Conductor> conductores = new ArrayList<>();
        conductores.add(new Conductor(null, "Juan", 123456789L, 5551234L, "Calle 1", bus1.getId(), horario1.getId()));
        conductores.add(new Conductor(null, "Pedro", 987654321L, 5555678L, "Calle 2", bus1.getId(), horario2.getId()));
        conductores.add(new Conductor(null, "Luis", 456123789L, 5558765L, "Calle 3", bus2.getId(), horario1.getId()));
        conductores.add(new Conductor(null, "Carlos", 789456123L, 5554321L, "Calle 4", bus2.getId(), horario2.getId()));
        conductores.add(new Conductor(null, "Ana", 321654987L, 5556789L, "Calle 5", bus1.getId(), horario1.getId()));
        conductores.add(new Conductor(null, "Maria", 654321789L, 5559876L, "Calle 6", bus1.getId(), horario2.getId()));
        conductores.add(new Conductor(null, "Jose", 321987654L, 5552345L, "Calle 7", bus2.getId(), horario1.getId()));
        conductores.add(new Conductor(null, "Rosa", 789123456L, 5558765L, "Calle 8", bus2.getId(), horario2.getId()));
        conductores.add(new Conductor(null, "David", 456789123L, 5556543L, "Calle 9", bus1.getId(), horario1.getId()));
        conductores.add(new Conductor(null, "Laura", 987321654L, 5553212L, "Calle 10", bus2.getId(), horario2.getId()));

        // Guarda los conductores
        repositorioConductor.saveAll(conductores);

        // Crea la asignación
        Asignacion asignacion = new Asignacion(null, conductores, List.of(bus1, bus2), List.of(horario1, horario2));

        // Guarda la asignación
        repositorioAsignacion.save(asignacion);
    }
}