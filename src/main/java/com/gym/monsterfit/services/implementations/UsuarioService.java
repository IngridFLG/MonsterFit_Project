package com.gym.monsterfit.services.implementations;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.Usuario;
import com.gym.monsterfit.exceptions.EmailExistsException;
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
        Usuario usuarioEntity = usuarioRepository.findByEmail(email);

        if (usuarioEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (usuarioEntity.getRol() != null) {
            authorities.add(new SimpleGrantedAuthority(usuarioEntity.getRol().getAuthority()));
        } else {
            throw new UsernameNotFoundException("Error en el Login: usuario '" + email + "' no tiene roles asignados!");
        }
        return new User(usuarioEntity.getEmail(), usuarioEntity.getEncryptedPassword(), authorities);
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null)
        throw new EmailExistsException("El correo electronico ya existe");

        Usuario usuarioEntity = new Usuario();
        BeanUtils.copyProperties(usuario, usuarioEntity);

        usuarioEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));

        usuarioEntity.setRol(rolRepository.findByAuthority("ROLE_CLIENTE"));

        Usuario storedUserDetails = usuarioRepository.save(usuarioEntity);

        UsuarioDTO userToReturn = new UsuarioDTO();
        BeanUtils.copyProperties(storedUserDetails, userToReturn);

        return userToReturn;
    }
    
}
