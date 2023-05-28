package com.gym.monsterfit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.services.implementations.UsuarioService;

@Controller
public class loginController {
    
	@Autowired
	UsuarioService usuarioService;

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
			return "index";
		} catch (Exception e) {
			System.out.println("Error al cargar la página de inicio: " + e.getMessage());
			return "redirect:/";
		}
	}
}
