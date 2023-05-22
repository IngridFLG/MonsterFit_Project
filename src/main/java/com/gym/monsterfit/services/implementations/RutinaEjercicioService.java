package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import com.gym.monsterfit.repositories.RutinaEjercicioRepository;
import com.gym.monsterfit.services.interfaces.RutinaEjercicioServiceInterface;

@Service
public class RutinaEjercicioService implements RutinaEjercicioServiceInterface{

	@Autowired
	private RutinaEjercicioRepository repository;
	
	@Override
	public List<RutinaEjercicioEntity> getAllRutinaEjercicio() {
		return repository.findAll();
	}

	@Override
	public RutinaEjercicioEntity getRutinaEjercicioById(RutinaEjercicioEntity rutina) {
		return repository.findById(rutina.getId()).orElse(null);
	}

	@Override
	public RutinaEjercicioEntity createRutinaEjercicio(RutinaEjercicioEntity rutina) {
		return repository.save(rutina);
	}

	@Override
	public RutinaEjercicioEntity updateRutinaEjercicio(RutinaEjercicioEntity rutina) {
		return repository.save(rutina);
	}

	@Override
	public void deleteRutinaEjercicio(Integer id) {
		repository.deleteById(id);
	}

}
