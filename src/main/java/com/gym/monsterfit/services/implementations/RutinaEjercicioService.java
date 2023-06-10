package com.gym.monsterfit.services.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import com.gym.monsterfit.entities.RutinaEntity;
import com.gym.monsterfit.repositories.RutinaEjercicioRepository;
import com.gym.monsterfit.services.interfaces.RutinaEjercicioServiceInterface;

@Service
public class RutinaEjercicioService implements RutinaEjercicioServiceInterface {

	@Autowired
	private RutinaEjercicioRepository repository;

	public RutinaEjercicioService(RutinaEjercicioRepository rutinaEjercicioRepository) {
		this.repository = rutinaEjercicioRepository;
	}

	@Override
	public List<RutinaEjercicioEntity> obtenerEjerciciosPorRutina(int rutinaId) {
		return repository.findByRutinaId(rutinaId);
	}

	@Override
	public List<RutinaEjercicioEntity> obtenerEjerciciosPorFecha(LocalDate fecha) {
		return repository.findByFecha(fecha);
	}

	@Override
	public void agregarEjercicioRutina(RutinaEjercicioEntity rutinaEjercicio) {
		repository.save(rutinaEjercicio);
	}

	@Override
	public void actualizarEjercicioRutina(RutinaEjercicioEntity rutinaEjercicio) {
		repository.save(rutinaEjercicio);
	}

	@Override
	public void eliminarEjercicioRutina(int rutinaEjercicioId) {
		repository.deleteById(rutinaEjercicioId);
	}

	public void saveRoutine(LocalDate date, List<Integer> exerciseIds, RutinaEntity rutinaId) {

		List<RutinaEjercicioEntity> routines = exerciseIds.stream().map(id -> {
			RutinaEjercicioEntity routine = new RutinaEjercicioEntity();
			routine.setFecha(date);
			routine.setEjercicio(new EjercicioEntity(id, null, null, null, null, null, null, null));
			routine.setRutina(rutinaId);
			return routine;
		}).collect(Collectors.toList());
		routines.forEach(routine -> {
			repository.findByEjercicioIdAndRutinaId(routine.getEjercicio().getId(), routine.getRutina().getId(), routine.getFecha())
					.orElseGet(() -> {
						repository.save(routine);
						return null;
					});
		});
		// repository.saveAll(routines);
	}

}