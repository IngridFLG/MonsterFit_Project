package com.gym.monsterfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.monsterfit.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Usuario findByEmail(String email);
}
