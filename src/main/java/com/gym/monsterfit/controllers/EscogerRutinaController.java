package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import com.gym.monsterfit.entities.RutinaEntity;
import com.gym.monsterfit.repositories.EjercicioRepository;
import com.gym.monsterfit.repositories.RutinaEjercicioRepository;
import com.gym.monsterfit.repositories.RutinaRepository;
import com.gym.monsterfit.services.implementations.RutinaEjercicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rutina")
public class EscogerRutinaController {

    @Autowired
    RutinaRepository rutinaRepository;
    
    @Autowired
    EjercicioRepository ejercicioRepository;
    
    @Autowired
    RutinaEjercicioService rutinaEjercicioService;

    @Autowired
    RutinaEjercicioRepository rutinaEjercicioRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        List<RutinaEntity> tiposRutina = rutinaRepository.findAll();
        
        model.addAttribute("tiposRutina", tiposRutina);
        return "admin/asignarRutina";
    }
    
    
    
    @PostMapping
    public String procesarSeleccionTipoRutina(@RequestParam("selectedOption") String rutina, @RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha1, Model modal) {
    	System.out.println("RUTINA: " + rutina);
    	RutinaEntity rutinaEntity = rutinaRepository.getReferenceById(Integer.parseInt(rutina));
    	System.out.println(rutinaEntity.getNombre());
    	System.out.println(fecha1);
    	
    	List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
        List<RutinaEjercicioEntity> rutinaEjercicios = rutinaEjercicioRepository.findByRutinaIdAndFecha(rutinaEntity.getId(), fecha1);
        modal.addAttribute("rutinaEjercicios", rutinaEjercicios);

    	modal.addAttribute("rutinaEntity", rutinaEntity);
    	modal.addAttribute("fecha", fecha1);
    	
    	modal.addAttribute("ejercicios", ejercicios);
    	
    	return "admin/agregarEjercicioRutina";
    }
    
    @GetMapping("/borrar/{id}")
    public String eliminarCategoria(@PathVariable("id") Integer id, Model model) {

        RutinaEjercicioEntity rutinaEjercicioEntity = rutinaEjercicioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rutina ejercicio no encontrada con id: " + id));
        rutinaEjercicioRepository.delete(rutinaEjercicioEntity);


        List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
        List<RutinaEjercicioEntity> rutinaEjercicios = rutinaEjercicioRepository.findByRutinaIdAndFecha(rutinaEjercicioEntity.getRutina().getId(), rutinaEjercicioEntity.getFecha());
        model.addAttribute("rutinaEjercicios", rutinaEjercicios);

    	model.addAttribute("rutinaEntity", rutinaEjercicioEntity.getRutina());
    	model.addAttribute("fecha", rutinaEjercicioEntity.getFecha());
    	
    	model.addAttribute("ejercicios", ejercicios);
        return "admin/agregarEjercicioRutina";
    }
    
    @PostMapping("/guardar")
    public String procesarFormRutina(
    		@RequestParam("inputDate") String fechaString,
    		@RequestParam("rutinaId") String rutinaIdString,
            @RequestParam("ejerciciosIds") List<Integer> ejerciciosIds) {
    	
    	ejerciciosIds = ejerciciosIds.stream().filter(Objects::nonNull).collect(Collectors.toList());
    	
    	for (Integer ejercicioId : ejerciciosIds) {
    	    System.out.println("Ejercicio ID: " + ejercicioId);
    	}
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        RutinaEntity rutina = new RutinaEntity();
        Integer id = Integer.parseInt(rutinaIdString);
        rutina.setId(id);
        rutinaEjercicioService.saveRoutine(fecha, ejerciciosIds, rutina);
    	return "redirect:/rutina";
    }

}
