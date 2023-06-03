package com.gym.monsterfit.repositories;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gym.monsterfit.entities.HistorialEntity;

public interface HistorialRepository extends JpaRepository<HistorialEntity, Integer>{

    List<HistorialEntity> findByFecha(LocalDate fecha);
}
