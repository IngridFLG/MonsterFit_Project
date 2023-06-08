package com.gym.monsterfit.controllers;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.monsterfit.entities.MiembroDTO;
import com.gym.monsterfit.entities.MiembroEntity;
import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.repositories.MiembroRepository;
import com.gym.monsterfit.repositories.UsuarioRepository;
import com.gym.monsterfit.services.implementations.MiembroService;


@Controller
@RequestMapping("/form")
public class FormUserController {

	
		@Autowired
		private MiembroService miembroService;
		@Autowired
		private MiembroRepository miembroRepository;
		
		@Autowired
		private UsuarioRepository usuarioRepository;
	
	    @GetMapping("/registrar")
		public String registrarMiembro(MiembroEntity miembroEntity) {

			return "cliente/formularioCliente";
		} 
		
		@PostMapping("/registrar")
		public String procesarRegistroMiembro(@Valid MiembroDTO miembroEntity, BindingResult result, Model model,  Principal principal) {
			
		

			if(result.hasErrors()) {
				return "cliente/formularioCliente";
			}
			String username = principal.getName();
			UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
			MiembroEntity miembro = new MiembroEntity();
			miembro.setUsuario(usuarioEntity);
			miembro.setNombre(miembroEntity.getNombre());
			miembro.setEdad(miembroEntity.getEdad());
			miembro.setSexo(miembroEntity.getSexo());
			miembro.setAltura(miembroEntity.getAltura());
			miembro.setPeso(miembroEntity.getPeso());
			miembroService.createMiembro(miembro);
			model.addAttribute("miembroEntity", miembroEntity);
		
			
		    
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




