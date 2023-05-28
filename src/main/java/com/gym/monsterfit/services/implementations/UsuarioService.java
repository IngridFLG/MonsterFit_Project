package com.gym.monsterfit.services.implementations;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.RolEntity;
import com.gym.monsterfit.entities.UsuarioEntity;
import com.gym.monsterfit.repositories.RolRepository;
import com.gym.monsterfit.repositories.UsuarioRepository;
import com.gym.monsterfit.services.interfaces.UsuarioServiceInterface;
import com.gym.monsterfit.shared.DTO.UsuarioDTO;

@Service("userService")
public class UsuarioService implements UsuarioServiceInterface {


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuario= usuarioRepository.findByEmail(email);

        if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<RolEntity> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
	}

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        
        UsuarioEntity usuario = new UsuarioEntity(usuarioDTO.getEmail(),bCryptPasswordEncoder.encode(usuarioDTO.getPassword()),
        Arrays.asList(new RolEntity( "ROLE_CLIENTE")));
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
    
}
