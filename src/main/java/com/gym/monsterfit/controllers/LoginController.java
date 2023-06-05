package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.MiembroEntity;
import com.gym.monsterfit.services.implementations.MiembroService;
import com.gym.monsterfit.services.interfaces.MiembroServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.services.interfaces.UsuarioServiceInterface;

@Controller
public class LoginController {

	@Autowired
	UsuarioServiceInterface usuarioService;

	@Autowired
	MiembroService miembroService;

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
			if (usuario.getRol() != null && usuario.getRol().getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/ejercicio/listar";

			} else {
				MiembroEntity miembro = usuario.getMiembro();
				if (miembro==null){
					return "redirect:/form/registrar";
				}
				else if(miembroService.esMiembro(usuario.getId())==true) {
					System.out.println("si funciona");
					return "cliente/ejerciciosRutinaMes";
				}
					System.out.println("no le sabemos");
					return "cliente/chooseRoutine";

			}

		} catch (Exception e) {
			System.out.println("Error al cargar la página de inicio: " + e.getMessage());
			return "redirect:/";
		}
	}

}
