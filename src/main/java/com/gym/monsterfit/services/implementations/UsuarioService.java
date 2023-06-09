package com.gym.monsterfit.services.implementations;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.UsuarioEntity;

import com.gym.monsterfit.repositories.RolRepository;
import com.gym.monsterfit.repositories.UsuarioRepository;
import com.gym.monsterfit.services.interfaces.UsuarioServiceInterface;
import com.gym.monsterfit.shared.DTO.UsuarioDTO;

@Service
public class UsuarioService implements UsuarioServiceInterface {


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioEntity usuario = selectUsuariobyEmail(email);
        if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (usuario.getRol() != null) {
            authorities.add(new SimpleGrantedAuthority(usuario.getRol().getAuthority()));
        } else {
            throw new UsernameNotFoundException("Error en el Login: usuario '" + email + "' no tiene roles asignados!");
        }
		return new User(usuario.getEmail(),usuario.getPassword(), authorities);
	}

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        
        UsuarioEntity usuario = new UsuarioEntity(usuarioDTO.getEmail(),bCryptPasswordEncoder.encode(usuarioDTO.getPassword()),
        rolRepository.findByAuthority("ROLE_CLIENTE"));
        usuarioRepository.save(usuario);

		return usuarioDTO;
         
    }

    @Override
	public UsuarioEntity selectUsuariobyEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<UsuarioEntity> getAllUsuario() {
		return usuarioRepository.findAll();
	}

	@Override
	public UsuarioEntity getUsuarioById(UsuarioEntity usuario) {
		return usuarioRepository.findById(usuario.getId()).orElse(null);
	}

	@Override
	public UsuarioEntity updateUsuario(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void deleteUsuario(Integer id) {
		usuarioRepository.deleteById(id);
	}



	@Override
	public UsuarioEntity optenerUsarioPorId(Integer id) {
		
		return usuarioRepository.getById(id);
	
		
		
	}


}
