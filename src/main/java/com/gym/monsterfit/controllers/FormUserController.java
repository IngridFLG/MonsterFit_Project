package com.gym.monsterfit.controllers;


import java.security.Principal;

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
		public String registrarMiembro(@RequestParam(value = "id", required = false) Integer id, Model model) {
			MiembroEntity miembroEntity = new MiembroEntity();
			if(id != null) {
				miembroEntity = miembroRepository.findById(id).get();
			}
			model.addAttribute("miembroEntity", miembroEntity);
			return "cliente/formularioCliente";
		} 
		
		@PostMapping("/registrar")
		public String procesarRegistroMiembro(@Valid MiembroEntity miembroEntity, BindingResult result, Model model,  Principal principal) {
			
			if(result.hasErrors()) {
				return "cliente/formularioCliente";
			}
			String username = principal.getName();
			UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
			miembroEntity.setUsuario(usuarioEntity);
	
			miembroService.createMiembro(miembroEntity);
			model.addAttribute("miembroEntity", miembroEntity);

		    return "cliente/chooseRoutine";
		}
		
		
		@GetMapping("/editar")
		public String procesarMiembroEditar(@RequestParam("id") Integer id, @ModelAttribute("miembroEntity") MiembroEntity miembroActualizado) {
			MiembroEntity miembroEntity = miembroRepository.findById(id).get();
			miembroEntity.setNombre(miembroActualizado.getNombre());
			miembroEntity.setEdad(miembroActualizado.getEdad());
			miembroEntity.setPeso(miembroActualizado.getPeso());
			miembroEntity.setSexo(miembroActualizado.getSexo());
			miembroEntity.setAltura(miembroActualizado.getAltura());
			miembroRepository.save(miembroEntity);

			return "cliente/chooseRoutine";
		}
		
		
		
		@GetMapping("/eliminar/{id}")
		public String eliminarMiembro(@PathVariable("id") Integer id) {
			miembroService.deleteMiembro(id);
			return "cliente/chooseRoutine";
		}
		
}




