package com.gym.monsterfit.services.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.shared.DTO.UsuarioDTO;

public interface UsuarioServiceInterface extends UserDetailsService {

	public UsuarioDTO createUsuario(UsuarioDTO usuario);

	public List<UsuarioEntity> getAllUsuario();

	public UsuarioEntity getUsuarioById(UsuarioEntity usuario);

	public UsuarioEntity updateUsuario(UsuarioEntity usuario);

	public void deleteUsuario(Integer id);

	public UsuarioEntity selectUsuariobyEmail(String email);
	
}
