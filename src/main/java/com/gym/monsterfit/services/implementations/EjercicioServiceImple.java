package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.repositories.EjercicioRepository;
import com.gym.monsterfit.services.interfaces.EjercicioService;

@Service
public class EjercicioServiceImple implements EjercicioService{

	@Autowired
	private EjercicioRepository repository;
	
	@Override
	public List<EjercicioEntity> getAllEjercicios() {
		return repository.findAll();
	}

	@Override
	public EjercicioEntity getEjercicioById(EjercicioEntity ejercicio) {
		return repository.findById(ejercicio.getId()).orElse(null);
	}

	@Override
	public EjercicioEntity createEjercicio(EjercicioEntity ejercicio) {
		return repository.save(ejercicio);
	}

	@Override
	public EjercicioEntity updateEjercicio(EjercicioEntity ejercicio) {
		return repository.save(ejercicio);
	}

	@Override
	public void deleteEjercicio(Integer id) {
		repository.deleteById(id);
	}

}
