package com.gym.monsterfit.controllers;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym.monsterfit.entities.MiembroEntity;
import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.repositories.MiembroRepository;
import com.gym.monsterfit.repositories.UsuarioRepository;
import com.gym.monsterfit.services.interfaces.UsuarioServiceInterface;

@Controller
public class LoginController {

	@Autowired
	UsuarioServiceInterface usuarioService;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	MiembroRepository miembroRepository;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	
	@GetMapping("/")
	public String inicio(Model modelo, Principal principal) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UsuarioEntity usuario = usuarioService.selectUsuariobyEmail(auth.getName());
			String username = principal.getName();
			UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
			MiembroEntity miembro = miembroRepository.findByUsuario_Id(usuarioEntity.getId());

			modelo.addAttribute("usuario", usuario);
			if (usuario.getRol() != null && usuario.getRol().getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/ejercicio/listar";

			} else {

				
				if(usuario.getMiembro()==null) {
					return "redirect:/form/registrar";
				}
				else {
					modelo.addAttribute("miembroEntity", miembro);
					return "redirect:/elegirRutina/"+ usuarioEntity.getId();
				}

			}
			
			

		} catch (Exception e) {
			System.out.println("Error al cargar la página de inicio: " + e.getMessage());
			return "redirect:/";
		}
	}

}
