package com.gym.monsterfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.monsterfit.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    
    Rol findByAuthority(String authority);
}
