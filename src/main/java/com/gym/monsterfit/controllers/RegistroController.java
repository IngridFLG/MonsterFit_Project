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

import com.gym.monsterfit.entities.UsuarioEntity;
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
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") @Valid  UsuarioDTO userDetails,
	BindingResult result, RedirectAttributes redirectAttrs) {
		
		UsuarioEntity usuarioEntity = usuarioService.selectUsuariobyEmail(userDetails.getEmail());
		UsuarioDTO usuario = new UsuarioDTO();
		if(usuarioEntity != null){
			BeanUtils.copyProperties(usuarioEntity, usuario);
		}
		if(userDetails.getEmail().equals(usuario.getEmail())){
			return "redirect:/registro?error";
		}

		usuarioService.createUsuario(userDetails);
		return "redirect:/registro?exito";
	}
}
