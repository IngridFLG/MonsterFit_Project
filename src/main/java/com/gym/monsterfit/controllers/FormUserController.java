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

import com.gym.monsterfit.entities.MiembroEntity;
	import com.gym.monsterfit.repositories.MiembroRepository;
	import com.gym.monsterfit.services.implementations.MiembroService;

import lombok.Data;

@Data
@Controller
@RequestMapping("/form")
public class FormUserController {

	
		@Autowired
		private MiembroService miembroService;
		@Autowired
		private MiembroRepository miembroRepository;
		
		boolean current;
		
		@GetMapping("/listar")
		public String listarMiembros(Model model) {
			List<MiembroEntity> miembros = miembroService.getAllMiembro();
			System.out.println("entro auqi get ejercicio listar");
			for(MiembroEntity miembro : miembros) {
				System.out.println(miembro.getNombre());
			}
			model.addAttribute("miembros", miembros);
			return "miembroListar";
		}
		
		
	    @GetMapping("/registrar")
		public String registrarMiembro(MiembroEntity miembroEntity) {
			return "formularioCliente";
		} 
		
		@PostMapping("/registrar")
		public String procesarRegistroMiembro(@Valid MiembroEntity miembroEntity, BindingResult result, Model model) {
			
			if(result.hasErrors()) {
				return "cliente/formularioCliente";
			}
			miembroService.createMiembro(miembroEntity);
			model.addAttribute("miembroEntity", miembroEntity);
			if(miembroService.getMiembroById(miembroEntity)!=null) {
				current = true;
			}
		    System.out.println(miembroEntity.getNombre() + "guarda?" +  current);
		    
		    return "cliente/ejerciciosRutinaDia";
		}
		
		
		
		@GetMapping("/editar/{id}")
		public String editarMiembro(MiembroEntity miembroEntity, Model model) {
			MiembroEntity miembro = miembroRepository.findById(miembroEntity.getId()).orElse(null);
			
			System.out.println(miembro.getNombre() + " editable");
			model.addAttribute("miembroEntity", miembroEntity);
			return "miembroRegistrar";
		}
		
		@PostMapping("/editar/{id}")
		public String procesarMiembroEditar(@PathVariable("id") Integer id, MiembroEntity miembroEntity) {
			miembroEntity.setId(id);
			miembroRepository.save(miembroEntity);
			return "redirect:/miembro/listar";
		}
		
		
		
		@GetMapping("/eliminar/{id}")
		public String eliminarMiembro(@PathVariable("id") Integer id) {
			miembroService.deleteMiembro(id);
			return "redirect:/miembro/listar";
		}
		
		
	}



