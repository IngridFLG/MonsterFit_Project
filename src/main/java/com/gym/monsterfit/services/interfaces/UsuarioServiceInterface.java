package com.gym.monsterfit.services.interfaces;



import org.springframework.security.core.userdetails.UserDetailsService;

import com.gym.monsterfit.shared.DTO.UsuarioDTO;

public interface  UsuarioServiceInterface  extends UserDetailsService{
    
    public UsuarioDTO crearUsuario(UsuarioDTO usuario);
}
