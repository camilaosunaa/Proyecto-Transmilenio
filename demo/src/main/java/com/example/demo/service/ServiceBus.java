package com.example.demo.service;

import com.example.demo.Conversion.BusDTOConverter;
import com.example.demo.DTO.BusDTO;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.repositories.RepositorioBus;
import com.example.demo.repositories.RepositorioConductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceBus  {
    @Autowired
    private RepositorioBus RB;

    @Autowired
    private RepositorioConductor conductorRepository;

    @Autowired
    private ServiceConductor serviceConductor;

    @Autowired
    private BusDTOConverter busDTOConverter;

    public BusDTO getBUs(Long id){
        return busDTOConverter.EntityToDTO(findBusById(id));
    }

    public List<BusDTO> recuperarBusPorIdConductor(Long idConductor){
        List<Bus> buses = recuperarTodosBuses();
        List<BusDTO> busesDTO = new ArrayList<>();
        for(Bus b:buses){
            if(b.getConductor().getId() == idConductor){
                BusDTO busDTO = busDTOConverter.EntityToDTO(b);
                busesDTO.add(busDTO);
            }
        }
        return busesDTO;
    }

    public Bus createBus(BusDTO busDTO){
        Bus bus = busDTOConverter.DTOToEntity(busDTO);
        return RB.save(bus);
    }

    public void deleteBus(Long idBus) {
        // Buscar el bus por ID
        Bus bus = findBusById(idBus);

        // Si el bus no existe, lanzar una excepción (opcional)
        if (bus == null) {
            throw new RuntimeException("El bus con el ID " + idBus + " no existe.");
        }

        // Verificación: Si el bus tiene un conductor asignado, no se puede eliminar
        if (bus.getConductor() != null) {
            throw new RuntimeException("No se puede eliminar el bus porque tiene un conductor asignado.");
        }

        // Eliminar el bus
        RB.deleteById(idBus);
    }

    public BusDTO UpdateBus(Long idBus, BusDTO busDTO) {
        // Buscar el bus actual para actualizar
        Bus bus = findBusById(idBus);

        // Si el bus no existe, lanzar una excepción (opcional)
        if (bus == null) {
            throw new RuntimeException("El bus con el ID " + idBus + " no existe.");
        }

        // Verificación: Si el bus tiene un conductor asignado, no se puede actualizar
        if (bus.getConductor() != null) {
            throw new RuntimeException("No se puede actualizar el bus porque ya tiene un conductor asignado.");
        }

        // Validar el conductor en el DTO (null o válido)
        if (busDTO.getIdConductor() != null && busDTO.getIdConductor() != 0) {
            Conductor conductor = serviceConductor.recuperarConductor(busDTO.getIdConductor());
            bus.setConductor(conductor);
        } else {
            bus.setConductor(null);  // Sin conductor asignado
        }

        // Convertir el DTO a entidad
        bus = busDTOConverter.DTOToEntity(busDTO);
        bus.setId(idBus);

        // Guardar y devolver el bus actualizado en formato DTO
        return busDTOConverter.EntityToDTO(RB.save(bus));
    }



    public Bus findBusById(Long id) {
        return RB.findById(id).orElse(null);
    }

    public List<Bus> recuperarTodosBuses(){

        return RB.findAll();
    }

    public List<Long> recuperarIdTodosBuses() {
        List<Bus> buses = RB.findAll();  // Obtener la lista de buses

        // Retornar una lista de IDs de los conductores (o null si no hay conductor)
        return buses.stream()
                .map(bus -> bus.getConductor() != null ? bus.getConductor().getId() : null)
                .collect(Collectors.toList());
    }
}
