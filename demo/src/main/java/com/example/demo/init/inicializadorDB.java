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
import com.example.demo.repositories.RepositorioAsignacion;
import com.example.demo.repositories.RepositorioBus;
import com.example.demo.repositories.RepositorioConductor;


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
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Elimina las tablas con dependencias primero
        jdbcTemplate.execute("DROP TABLE IF EXISTS ruta_estaciones");
        jdbcTemplate.execute("DROP TABLE IF EXISTS ruta");
        jdbcTemplate.execute("DROP TABLE IF EXISTS asignacion CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS conductor CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS bus CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS horario CASCADE");

// Crear las tablas necesarias
        jdbcTemplate.execute("CREATE TABLE conductor (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    nombre VARCHAR(255) NOT NULL,\n" +
                "    cedula BIGINT NOT NULL,\n" +
                "    telefono BIGINT NOT NULL,\n" +
                "    direccion VARCHAR(255) NOT NULL)");

        jdbcTemplate.execute("CREATE TABLE horario (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    diasemana VARCHAR(255) NOT NULL,\n" +
                "    dia INT NOT NULL,\n" +
                "    mes INT NOT NULL,\n" +
                "    año INT NOT NULL,\n" +
                "    hora_inicio TIME NOT NULL,\n" +
                "    hora_final TIME NOT NULL)");

        jdbcTemplate.execute("CREATE TABLE bus (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    placa VARCHAR(255) NOT NULL,\n" +
                "    modelo VARCHAR(255) NOT NULL,\n" +
                "    id_conductor BIGINT,\n" +
                "    FOREIGN KEY (id_conductor) REFERENCES conductor(id))");

        jdbcTemplate.execute("CREATE TABLE asignacion (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY)");

        jdbcTemplate.execute("CREATE TABLE ruta (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    codigo VARCHAR(255) NOT NULL,\n" +
                "    idbus BIGINT NOT NULL,\n" +
                "    idhorario BIGINT NOT NULL,\n" +
                "    FOREIGN KEY (idbus) REFERENCES bus(id),\n" +
                "    FOREIGN KEY (idhorario) REFERENCES horario(id))");

        jdbcTemplate.execute("CREATE TABLE ruta_estaciones (\n" +
                "    ruta_id BIGINT NOT NULL,\n" +
                "    estacion VARCHAR(255) NOT NULL,\n" +
                "    FOREIGN KEY (ruta_id) REFERENCES ruta(id))");


        // Crea  los horarios
        Horario horario1 = new Horario(null,"lunes", 14, 10, 2024, LocalTime.of(8, 0), LocalTime.of(16, 0));
        Horario horario2 = new Horario(null, "martes ",10, 10, 2024, LocalTime.of(9, 0), LocalTime.of(17, 0));
        Horario horario3 = new Horario(null,"miercoles", 12, 10, 2024, LocalTime.of(8, 30), LocalTime.of(16, 30));
        Horario horario4 = new Horario(null,"jueves", 15, 10, 2024, LocalTime.of(7, 30), LocalTime.of(15, 30));
        Horario horario5 = new Horario(null, "viernes",16, 10, 2024, LocalTime.of(9, 0), LocalTime.of(18, 0));
        Horario horario6 = new Horario(null,"sabado", 18, 10, 2024, LocalTime.of(10, 0), LocalTime.of(17, 0));
        Horario horario7 = new Horario(null, "domingo",20, 10, 2024, LocalTime.of(7, 0), LocalTime.of(15, 0));
        Horario horario8 = new Horario(null,"lunes", 21, 10, 2024, LocalTime.of(9, 30), LocalTime.of(18, 30));
        Horario horario9 = new Horario(null,"martes", 22, 10, 2024, LocalTime.of(8, 0), LocalTime.of(16, 0));
        Horario horario10 = new Horario(null, "miercoles",23, 10, 2024, LocalTime.of(10, 0), LocalTime.of(17, 30));

// Guardar los horarios en el repositorio
        horario1 = repositorioHorario.save(horario1);
        horario2 = repositorioHorario.save(horario2);
        horario3 = repositorioHorario.save(horario3);
        horario4 = repositorioHorario.save(horario4);
        horario5 = repositorioHorario.save(horario5);
        horario6 = repositorioHorario.save(horario6);
        horario7 = repositorioHorario.save(horario7);
        horario8 = repositorioHorario.save(horario8);
        horario9 = repositorioHorario.save(horario9);
        horario10 = repositorioHorario.save(horario10);

        List<Conductor> conductores = new ArrayList<>();
        conductores.add(new Conductor(null, "Juan", 123456789L, 5551234L, "Calle 1"));
        conductores.add(new Conductor(null, "Pedro", 987654321L, 5555678L, "Calle 2"));
        conductores.add(new Conductor(null, "Luis", 456123789L, 5558765L, "Calle 3"));
        conductores.add(new Conductor(null, "Carlos", 789456123L, 5554321L, "Calle 4"));
        conductores.add(new Conductor(null, "Sofia", 321654987L, 5557890L, "Calle 5"));
        conductores.add(new Conductor(null, "Ana", 654987321L, 5556789L, "Calle A"));
        conductores.add(new Conductor(null, "Marta", 852963741L, 5552345L, "Calle B"));
        conductores.add(new Conductor(null, "Javier", 741258963L, 5556781L, "Calle C"));
        conductores.add(new Conductor(null, "José", 963852741L, 5551235L, "Calle D"));
        conductores.add(new Conductor(null, "Elena", 159753486L, 5557896L, "Calle E"));

        repositorioConductor.saveAll(conductores);

        Bus bus1 = new Bus(null, "ABC123", "Modelo1",conductores.get(1));
        Bus bus2 = new Bus(null, "DEF456", "Modelo2",conductores.get(2));
        Bus bus3 = new Bus(null, "XYZ", "modelo3", conductores.get(3));
        Bus bus4 = new Bus(null,"DGH", "Modelo4",conductores.get(5));
        Bus bus5 = new Bus(null, "QUL","modelo 5",conductores.get(6));
        repositorioBus.save(bus1);
        repositorioBus.save(bus2);
        repositorioBus.save(bus3);
        repositorioBus.save(bus4);
        repositorioBus.save(bus5);

        // Crea las rutas y asigna los buses
        Ruta ruta1 = new Ruta(null, "RUTA-01", new ArrayList<>(),bus1, horario2);
        Ruta ruta2 = new Ruta(null, "RUTA-02", new ArrayList<>(), bus1,horario1);
        Ruta ruta3 = new Ruta(null, "RUTA-03", new ArrayList<>(), bus2,horario3);
        Ruta ruta4 = new Ruta(null, "RUTA-04", new ArrayList<>(), bus2,horario4);
        Ruta ruta5 = new Ruta(null, "RUTA-05", new ArrayList<>(), bus3,horario5);
        Ruta ruta6 = new Ruta(null, "RUTA-06", new ArrayList<>(), bus3,horario6);
        Ruta ruta7 = new Ruta(null, "RUTA-07", new ArrayList<>(), bus3,horario7);
        Ruta ruta8 = new Ruta(null, "RUTA-08", new ArrayList<>(), bus4,horario8);
        Ruta ruta9 = new Ruta(null, "RUTA-09", new ArrayList<>(), bus5,horario9);
        Ruta ruta10 = new Ruta(null, "RUTA-010", new ArrayList<>(), bus5,horario10);


        // Guarda las rutas
         repositorioRuta.save(ruta1);
         repositorioRuta.save(ruta2);

        // Actualiza las rutas con estaciones
        ruta1.setEstaciones(List.of("Estación A", "Estación B", "Estación C"));
        ruta2.setEstaciones(List.of("Estación X", "Estación Y", "Estación Z"));
        ruta3.setEstaciones(List.of("Estación D", "Estación E", "Estación F"));
        ruta4.setEstaciones(List.of("Estación G", "Estación H", "Estación I"));
        ruta5.setEstaciones(List.of("Estación J", "Estación K", "Estación L"));
        ruta6.setEstaciones(List.of("Estación M", "Estación N", "Estación O"));
        ruta7.setEstaciones(List.of("Estación P", "Estación Q", "Estación R"));
        ruta8.setEstaciones(List.of("Estación S", "Estación T", "Estación U"));
        ruta9.setEstaciones(List.of("Estación V", "Estación W", "Estación X"));
        ruta10.setEstaciones(List.of("Estación Y", "Estación Z", "Estación A"));
        repositorioRuta.save(ruta1);
        repositorioRuta.save(ruta2);
        
        repositorioBus.save(bus1);
        repositorioBus.save(bus2);


        // Crea la asignación
        Asignacion asignacion = new Asignacion(null, conductores, List.of(bus1, bus2), List.of(horario1, horario2));

        // Guarda la asignación
        repositorioAsignacion.save(asignacion);
    }
}

