package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.TipoEntity;
import com.gym.monsterfit.repositories.TipoRepository;
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
    TipoRepository tipoRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        List<TipoEntity> tiposRutina = tipoRepository.findAll();
        
        model.addAttribute("tiposRutina", tiposRutina);
        return "admin/asignarRutina";
    }
    
    
    
    @PostMapping
    public String procesarSeleccionTipoRutina(@RequestParam("selectedOption") String rutina, @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha1, Model modal) {
    	TipoEntity tipoEntity = tipoRepository.getReferenceById(Integer.parseInt(rutina));
    	System.out.println(tipoEntity.getNombre());
    	System.out.println(fecha1);
    	
    	
    	modal.addAttribute("tipoEntity", tipoEntity);
    	modal.addAttribute("fecha", fecha1);
    	
    	return "admin/agregarEjercicioRutina";
    }


}
