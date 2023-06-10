package com.gym.monsterfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import com.gym.monsterfit.entities.RutinaEntity;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicioEntity, Integer> {

	List<RutinaEjercicioEntity> findByRutinaId(Integer rutinaId);

	List<RutinaEjercicioEntity> findByFecha(LocalDate fecha);

	List<RutinaEjercicioEntity> findByRutinaIdAndFecha(Integer rutinaId, LocalDate fecha);

	void deleteById(Integer rutinaEjercicioId);

	boolean existsByRutinaAndEjercicioAndFecha(RutinaEntity rutina, EjercicioEntity ejercicio, LocalDate fecha);

}