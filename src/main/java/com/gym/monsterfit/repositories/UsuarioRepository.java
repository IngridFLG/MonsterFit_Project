package com.gym.monsterfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.monsterfit.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    
    UsuarioEntity findByEmail(String email);
}
