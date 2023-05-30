package com.gym.monsterfit.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		System.out.println("entro auqi get ejercicio listar");
		for(EjercicioEntity ejercicio : ejercicios) {
			System.out.println(ejercicio.getDescripcion());
		}
		model.addAttribute("ejercicios", ejercicios);
		return "ejercicioListar";
	}
	
	
	@GetMapping("/registrar")
	public String registrarEjercicio(EjercicioEntity ejercicioEntity) {
		return "ejercicioRegistrar";
	}
	
	
	@PostMapping("/registrar")
	public String procesarRegistroEjercicio(@Valid EjercicioEntity ejercicioEntity, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "ejercicioListar";
		}
		ejercicioRepository.save(ejercicioEntity);
		return "redirect:/ejercicio/listar";
	}
	
	
	
	@GetMapping("/editar/{id}")
	public String editarEjercicio(EjercicioEntity ejercicioEntity, Model model) {
		EjercicioEntity ejercicio = ejercicioRepository.findById(ejercicioEntity.getId()).orElse(null);
		System.out.println(ejercicio.getDescripcion() + " editable");
		model.addAttribute("ejercicioEntity", ejercicio);
		return "ejercicioRegistrar";
	}
	
	@PostMapping("/editar/{id}")
	public String procesarEjercicioEditar(@PathVariable("id") Integer id, EjercicioEntity ejercicioEntity) {
		ejercicioEntity.setId(id);
		ejercicioRepository.save(ejercicioEntity);
		return "redirect:/ejercicio/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarEjercicio(@PathVariable("id") Integer id) {
		ejercicioRepository.deleteById(id);
		return "redirect:/ejercicio/listar";
	}
	
}
