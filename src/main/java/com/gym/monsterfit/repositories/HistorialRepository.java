package com.gym.monsterfit.repositories;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gym.monsterfit.entities.HistorialEntity;
import com.gym.monsterfit.entities.MiembroEntity;
import com.gym.monsterfit.entities.RutinaEntity;

public interface HistorialRepository extends JpaRepository<HistorialEntity, Integer>{

    List<HistorialEntity> findByFecha(LocalDate fecha);

    List<HistorialEntity> findByMiembroAndRutina(MiembroEntity miembroEntity, RutinaEntity rutinaEntity);
}
