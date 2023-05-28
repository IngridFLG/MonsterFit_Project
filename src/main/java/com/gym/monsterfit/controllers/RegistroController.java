package com.gym.monsterfit.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.monsterfit.models.request.UsuarioRequest;
import com.gym.monsterfit.services.implementations.UsuarioService;
import com.gym.monsterfit.shared.DTO.UsuarioDTO;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
	private UsuarioService usuarioService;


	@ModelAttribute("usuario")
	public UsuarioDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "signin";
	}

	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") @Valid UsuarioRequest userDetails,
			BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			redirectAttrs.addFlashAttribute("error", "Error al registrar usuario. Por favor revise los campos.");
			return "redirect:/signin?error";
		}
		try {
            UsuarioDTO userDTO = new UsuarioDTO();
            BeanUtils.copyProperties(userDetails, userDTO);
			usuarioService.createUsuario(userDTO);
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("error",
					"Error al registrar usuario. Por favor intenta de nuevo más tarde.");
			return "redirect:/signin?error";
		}
		redirectAttrs.addFlashAttribute("exito", "Usuario registrado exitosamente.");
		return "redirect:/signin?exito";
	}
}
