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
    private RepositorioRuta repositorioRuta;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {// Eliminar las tablas existentes
        jdbcTemplate.execute("DROP TABLE IF EXISTS ruta_estaciones");
        jdbcTemplate.execute("DROP TABLE IF EXISTS ruta CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS asignacion CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS conductor CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS bus CASCADE");

// Crear las tablas necesarias

// Crear tabla conductor
        jdbcTemplate.execute("CREATE TABLE conductor (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    nombre VARCHAR(255) NOT NULL,\n" +
                "    cedula BIGINT NOT NULL,\n" +
                "    telefono BIGINT NOT NULL,\n" +
                "    direccion VARCHAR(255) NOT NULL)");

// Crear tabla bus
        jdbcTemplate.execute("CREATE TABLE bus (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    placa VARCHAR(255) NOT NULL,\n" +
                "    modelo VARCHAR(255) NOT NULL,\n" +
                "    id_conductor BIGINT NULL,\n" +
                "    FOREIGN KEY (id_conductor) REFERENCES conductor(id))");

// Crear tabla asignacion
        jdbcTemplate.execute("CREATE TABLE asignacion (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY)");

// Crear tabla ruta
        jdbcTemplate.execute("CREATE TABLE ruta (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    codigo VARCHAR(255) NOT NULL,\n" +
                "    idbus BIGINT NULL,\n" +
                "    horario VARCHAR(255) NOT NULL,\n" +
                "    FOREIGN KEY (idbus) REFERENCES bus(id))");

// Crear tabla ruta_estaciones
        jdbcTemplate.execute("CREATE TABLE ruta_estaciones (\n" +
                "    ruta_id BIGINT NOT NULL,\n" +
                "    estacion VARCHAR(255) NOT NULL,\n" +
                "    FOREIGN KEY (ruta_id) REFERENCES ruta(id))");


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

        Bus bus1 = new Bus(null, "ABC123", "Modelo1",conductores.get(0));
        Bus bus2 = new Bus(null, "DEF456", "Modelo2",conductores.get(1));
        Bus bus3 = new Bus(null, "XYZ", "modelo3", conductores.get(0));
        Bus bus4 = new Bus(null,"DGH", "Modelo4",conductores.get(5));
        Bus bus5 = new Bus(null, "QUL","modelo 5",conductores.get(6));
        repositorioBus.save(bus1);
        repositorioBus.save(bus2);
        repositorioBus.save(bus3);
        repositorioBus.save(bus4);
        repositorioBus.save(bus5);

        // Crea las rutas y asigna los buses
        Ruta ruta1 = new Ruta(null, "RUTA-01", new ArrayList<>(),bus1, "horario2");
        Ruta ruta2 = new Ruta(null, "RUTA-02", new ArrayList<>(), bus1,"horario1");
        Ruta ruta3 = new Ruta(null, "RUTA-03", new ArrayList<>(), bus2,"horario3");
        Ruta ruta4 = new Ruta(null, "RUTA-04", new ArrayList<>(), bus2,"horario4");
        Ruta ruta5 = new Ruta(null, "RUTA-05", new ArrayList<>(), bus3,"horario5");
        Ruta ruta6 = new Ruta(null, "RUTA-06", new ArrayList<>(), bus3,"horario6");
        Ruta ruta7 = new Ruta(null, "RUTA-07", new ArrayList<>(), bus3,"horario7");
        Ruta ruta8 = new Ruta(null, "RUTA-08", new ArrayList<>(), bus4,"horario8");
        Ruta ruta9 = new Ruta(null, "RUTA-09", new ArrayList<>(), bus5,"horario9");
        Ruta ruta10 = new Ruta(null, "RUTA-010", new ArrayList<>(), bus5,"horario10");


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
        repositorioRuta.save(ruta3);
        repositorioRuta.save(ruta4);
        repositorioRuta.save(ruta5);
        repositorioRuta.save(ruta6);
        repositorioRuta.save(ruta7);
        repositorioRuta.save(ruta8);
        repositorioRuta.save(ruta9);
        repositorioRuta.save(ruta10);
        
        repositorioBus.save(bus1);
        repositorioBus.save(bus2);
        repositorioBus.save(bus3);
        repositorioBus.save(bus4);
        repositorioBus.save(bus5);


        // Crea la asignación
        Asignacion asignacion = new Asignacion(null, conductores, List.of(bus1, bus2,bus3,bus4,bus5),List.of(ruta1,ruta2,ruta3,ruta4,ruta5,ruta6,ruta7,ruta8,ruta9,ruta10));

        // Guarda la asignación
        repositorioAsignacion.save(asignacion);
    }
}

