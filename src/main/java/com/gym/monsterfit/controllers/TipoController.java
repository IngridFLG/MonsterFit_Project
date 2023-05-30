package com.gym.monsterfit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.monsterfit.entities.TipoEntity;
import com.gym.monsterfit.repositories.TipoRepository;

@Controller
@RequestMapping("/tipo")
public class TipoController {

	@Autowired
	private TipoRepository tipoRepository;
	
	@GetMapping("/listar")
	public String listarTipo(Model model) {
		System.out.println("entro aqui");
		
		List<TipoEntity> tipos = tipoRepository.findAll();
		model.addAttribute("tipos", tipos);
		return "tipoListar";
	}
	
	@GetMapping("/crear")
	public String mostrarFormulario(TipoEntity tipoEntity) {
		System.out.println("entro en get crear");
		return "tipoCrear";
	}
	
	@PostMapping("/crear")
	public String procesarFormulario(@Valid TipoEntity tipoEntity, BindingResult result, Model model) {
		System.out.println("entro en post crear");
		if(result.hasErrors()) {
			return "tipoCrear";
		}
		TipoEntity tipoNuevo = tipoRepository.save(tipoEntity);
		model.addAttribute("tipoEntity", tipoNuevo);
		return "redirect:/tipo/listar";
	}
	
	
	
	
	@GetMapping("/editar/{id}")
	public String editarTipo(TipoEntity tipoEntity, Model model) {
		System.out.println("entro en get editar");
		TipoEntity tipo = tipoRepository.findById(tipoEntity.getId()).orElse(null);
		
		if(tipo == null) {
			return "tipoNoExiste";
		}
		model.addAttribute("tipoEntity", tipo);
		System.out.println("llego al return editar get");
		return "tipoCrear";
	}
	
	@PostMapping("/editar/{id}")
	public String guardarCambiosTipo(@PathVariable("id") Integer id, TipoEntity tipoEntity) {
		tipoEntity.setId(id);
		tipoRepository.save(tipoEntity);
		return "redirect:/tipo/listar";
	}
	
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminarTipo(@PathVariable("id") Integer id, TipoEntity tipoEntity) {
		tipoEntity.setId(id);
		tipoRepository.delete(tipoEntity);
		return "redirect:/tipo/listar";
	}
	
	
}
