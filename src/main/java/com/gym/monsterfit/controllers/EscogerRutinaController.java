package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.entities.RutinaEntity;
import com.gym.monsterfit.repositories.EjercicioRepository;
import com.gym.monsterfit.repositories.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/rutina")
public class EscogerRutinaController {

    @Autowired
    RutinaRepository rutinaRepository;
    
    @Autowired
    EjercicioRepository ejercicioRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        List<RutinaEntity> tiposRutina = rutinaRepository.findAll();
        
        model.addAttribute("tiposRutina", tiposRutina);
        return "admin/asignarRutina";
    }
    
    
    
    @PostMapping
    public String procesarSeleccionTipoRutina(@RequestParam("selectedOption") String rutina, @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha1, Model modal) {
    	RutinaEntity RutinaEntity = rutinaRepository.getReferenceById(Integer.parseInt(rutina));
    	System.out.println(RutinaEntity.getNombre());
    	System.out.println(fecha1);
    	
    	List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
    	modal.addAttribute("RutinaEntity", RutinaEntity);
    	modal.addAttribute("fecha", fecha1);
    	
    	modal.addAttribute("ejercicios", ejercicios);
    	
    	return "admin/agregarEjercicioRutina";
    }


}
