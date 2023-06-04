package com.gym.monsterfit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.monsterfit.entities.RolEntity;
import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.repositories.RolRepository;
import com.gym.monsterfit.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
@RequestMapping("/administrarClientes")
public class AdministrarClientesController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping
    public String verClientes(Model model){
        Integer id = 2;
        RolEntity rol = rolRepository.findById(id).get();
        List<UsuarioEntity> clientes = usuarioRepository.findByRol(rol);
        model.addAttribute("clientes", clientes);
        return "admin/verClientes";
    }

    @GetMapping("/eliminar/{id}")
	public String eliminarEjercicio(@PathVariable("id") Integer id) {
		usuarioRepository.deleteById(id);
		return "redirect:/administrarClientes";
	}

    @GetMapping("/formulario")
	public String formularioEjercicio(@RequestParam(value = "id") Integer id, Model model) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario = usuarioRepository.findById(id).get();
		model.addAttribute("usuario", usuario);
		return "admin/editarCliente";
	}

    @PostMapping("/editar")
	public String editarEjercicio(@RequestParam("id") Integer id, @ModelAttribute("usuario") UsuarioEntity usuarioEntity, Model model) {
		
		UsuarioEntity usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Ejercicio no encontrado con id: " + id));

		usuario.setPassword(passwordEncoder.encode(usuarioEntity.getPassword()));
        usuarioRepository.save(usuario);
		 
		return "redirect:/administrarClientes";
	}
}
