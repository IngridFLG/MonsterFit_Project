package com.gym.monsterfit.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.models.request.UsuarioRequest;
import com.gym.monsterfit.models.response.UsuarioResponse;
import com.gym.monsterfit.services.implementations.UsuarioService;
import com.gym.monsterfit.shared.DTO.UsuarioDTO;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
 
    @Autowired
    UsuarioService userService;

    @PostMapping("/crear")
    public UsuarioResponse crearUusuario(@RequestBody UsuarioRequest userDetails){
        UsuarioResponse userToReturn = new UsuarioResponse();
        UsuarioDTO userDto = new UsuarioDTO();
        BeanUtils.copyProperties(userDetails, userDto);

        UsuarioDTO createUserDto = userService.createUsuario(userDto);
        BeanUtils.copyProperties(createUserDto, userToReturn);
        return userToReturn;
    }

    @GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	@GetMapping("/")
	public String inicio(Model modelo) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UsuarioEntity usuario = userService.selectUsuariobyEmail(auth.getName());
			modelo.addAttribute("usuario", usuario);
			return "index";
		} catch (Exception e) {
			System.out.println("Error al cargar la página de inicio: " + e.getMessage());
			return "redirect:/";
		}
	}
}
