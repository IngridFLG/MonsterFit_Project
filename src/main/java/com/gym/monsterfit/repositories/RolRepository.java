package com.gym.monsterfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.monsterfit.entities.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    
    RolEntity findByAuthority(String authority);
}
