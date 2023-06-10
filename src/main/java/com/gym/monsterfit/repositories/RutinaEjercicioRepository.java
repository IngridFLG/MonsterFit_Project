package com.gym.monsterfit.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import com.gym.monsterfit.entities.RutinaEntity;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicioEntity, Integer> {

	List<RutinaEjercicioEntity> findByRutinaId(Integer rutinaId);

	List<RutinaEjercicioEntity> findByFecha(LocalDate fecha);

	void deleteById(Integer rutinaEjercicioId);
	
	@Query("SELECT re FROM RutinaEjercicioEntity re " +
	           "WHERE re.ejercicio.id = :ejercicioId AND re.rutina.id = :rutinaId AND re.fecha = :fecha ")
	    Optional<RutinaEjercicioEntity> findByEjercicioIdAndRutinaId(
	            @Param("ejercicioId") Integer ejercicioId, @Param("rutinaId") Integer rutinaId, @Param("fecha") LocalDate fecha);
}
