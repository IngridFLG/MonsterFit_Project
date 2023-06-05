package com.gym.monsterfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicioEntity, Integer>{
    
    List<RutinaEjercicioEntity> findByFecha(LocalDate fecha);
}
