package com.gym.monsterfit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.repositories.EjercicioRepository;

@Controller
@RequestMapping("/ejercicio")
public class EjercicioController {

	@Autowired
	private EjercicioRepository ejercicioRepository;
	
	@GetMapping("/listar")
	public String listarEjercicios(Model model) {
		List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
		
		model.addAttribute("ejercicios", ejercicios);
		return "admin/listarEjercicio";
	}
	
	
	@GetMapping("/formulario")
	public String formularioEjercicio(@RequestParam(value = "id", required = false) Integer id, Model model) {
		EjercicioEntity ejercicioEntity = new EjercicioEntity();
		if(id != null) {
			ejercicioEntity = ejercicioRepository.findById(id).get();
		}
		model.addAttribute("ejercicio", ejercicioEntity);
		return "admin/agregarEjercicio";
	}

	@PostMapping("/registrar")
	public String agregarEjercicio(@ModelAttribute("ejercicio") @Valid EjercicioEntity ejercicioEntity, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "admin/agregarEjercicio";
		}
		EjercicioEntity ejercicio = ejercicioRepository.findByNombre(ejercicioEntity.getNombre());
		if (ejercicio != null) {
            model.addAttribute("nombreDuplicado", true);
            return "admin/agregarEjercicio";
        }

		ejercicioRepository.save(ejercicioEntity);
		return "redirect:/ejercicio/listar";
	}
	
	@GetMapping("/editar")
	public String editarEjercicio(@RequestParam("id") Integer id, @ModelAttribute("ejercicio") EjercicioEntity ejercicioEntity, Model model) {
		
		EjercicioEntity ejercicio = ejercicioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Ejercicio no encontrado con id: " + id));
		if (ejercicio == null) {
            model.addAttribute("nombreDuplicado", true);
            return "admin/agregarEjercicio";
        }
		
		ejercicio.setNombre(ejercicioEntity.getNombre());
		ejercicio.setUrl(ejercicioEntity.getUrl());
		ejercicio.setPeso(ejercicio.getPeso());
		ejercicio.setRepeticiones(ejercicioEntity.getRepeticiones());
		ejercicio.setSeries(ejercicioEntity.getSeries());
		ejercicio.setTiempo(ejercicioEntity.getTiempo());
		ejercicioRepository.save(ejercicio);
		 
		return "redirect:/ejercicio/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarEjercicio(@PathVariable("id") Integer id) {
		ejercicioRepository.deleteById(id);
		return "redirect:/ejercicio/listar";
	}
	
}
