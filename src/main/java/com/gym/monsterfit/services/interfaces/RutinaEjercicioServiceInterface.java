package com.gym.monsterfit.services.interfaces;

import java.time.LocalDate;
import java.util.List;
import com.gym.monsterfit.entities.RutinaEjercicioEntity;

public interface RutinaEjercicioServiceInterface {

	List<RutinaEjercicioEntity> obtenerEjerciciosPorRutina(int rutinaId);

	List<RutinaEjercicioEntity> obtenerEjerciciosPorFecha(LocalDate fecha);

	void agregarEjercicioRutina(RutinaEjercicioEntity rutinaEjercicio);

	void actualizarEjercicioRutina(RutinaEjercicioEntity rutinaEjercicio);

	void eliminarEjercicioRutina(int ejercicioId);
}