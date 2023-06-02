package com.gym.monsterfit.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.monsterfit.entities.EjercicioEntity;

@Repository
public interface EjercicioRepository extends JpaRepository<EjercicioEntity, Integer>{

    EjercicioEntity findByNombre(String nombre);
}
