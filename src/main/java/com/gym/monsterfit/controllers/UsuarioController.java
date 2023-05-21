package com.gym.monsterfit.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

        UsuarioDTO createUserDto = userService.crearUsuario(userDto);
        BeanUtils.copyProperties(createUserDto, userToReturn);
        return userToReturn;
    }
}
