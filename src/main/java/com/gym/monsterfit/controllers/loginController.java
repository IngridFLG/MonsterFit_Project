package com.gym.monsterfit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.services.interfaces.UsuarioServiceInterface;

@Controller
public class loginController {
    
	@Autowired
	UsuarioServiceInterface usuarioService;

    @GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	

	@GetMapping("/")
	public String inicio(Model modelo) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UsuarioEntity usuario = usuarioService.selectUsuariobyEmail(auth.getName());
			modelo.addAttribute("usuario", usuario);
			boolean tieneRolAdmin = usuario.getRoles().stream().anyMatch(rol -> rol.getAuthority().equals("ROLE_ADMIN"));
			if (tieneRolAdmin) {
				return "adminHome";
				
			} else {
				return "form";
				
			}
		} catch (Exception e) {
			System.out.println("Error al cargar la página de inicio: " + e.getMessage());
			return "redirect:/";
		}
	}
}
