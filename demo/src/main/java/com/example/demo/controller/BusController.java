package com.example.demo.controller;

import com.example.demo.init.inicializadorDB;
import com.example.demo.modelo.Bus;
import com.example.demo.modelo.Conductor;
import com.example.demo.service.ServiceBus;
import com.example.demo.service.ServiceConductor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bus")
public class BusController {
    @Autowired
    private ServiceBus serviceBus;

    @Autowired
    private  ServiceConductor serviceConductor;

    @GetMapping("/list")
    public ModelAndView ListaBuses(){
        List<Bus> buses = serviceBus.ListaBuses();
        ModelAndView modelAndView = new ModelAndView("Bus-list");
        modelAndView.addObject("buses",buses);
        return modelAndView;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditar(@PathVariable Long id){
        Bus bus=serviceBus.recuperarbus(id);
        ModelAndView modelAndView = new ModelAndView("Bus-edit");
        modelAndView.addObject("bus",bus);
        return modelAndView;
    }

    @GetMapping("/add-form")
    public ModelAndView formularioAgregar() {
        Bus nuevoBus = new Bus();
        ModelAndView modelAndView = new ModelAndView("Bus-add");
        modelAndView.addObject("bus", nuevoBus);
        return modelAndView;
    }



    @PostMapping("/save")
    public Object guardarBus(@Valid @ModelAttribute Bus bus, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("Bus-edit")
                    .addObject("bus", bus);
        }

        serviceBus.guardarBus(bus);

        return new RedirectView("/bus/list");
    }

    @PostMapping("/delete/{id}")
    public Object eliminarBus(@PathVariable Long id){
        List<Conductor> conductores = serviceConductor.recuperarTodoConductor();

        for(Conductor c : conductores){
            if(c.getId_bus() != id){
                serviceBus.eliminarBus(id);
            }
        }

        return  new RedirectView("/bus/list");
    }
}

