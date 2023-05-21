package com.gym.monsterfit.services.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gym.monsterfit.shared.DTO.UsuarioDTO;

public interface UsuarioService extends UserDetailsService {

	public UsuarioDTO createUsuario(UsuarioDTO usuario);

	public List<UsuarioDTO> getAllUsuario();

	public UsuarioDTO getUsuarioById(UsuarioDTO usuario);

	public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuario);

	public void deleteUsuario(Integer id);
}
