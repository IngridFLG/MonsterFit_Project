package com.gym.monsterfit.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.monsterfit.entities.RolEntity;
import com.gym.monsterfit.entities.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    
   
    UsuarioEntity findByEmail(String email);

    List<UsuarioEntity> findByRol(RolEntity rol);
    
   
}
