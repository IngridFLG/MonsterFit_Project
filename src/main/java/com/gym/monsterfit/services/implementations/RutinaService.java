package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.RutinaEntity;
import com.gym.monsterfit.repositories.RutinaRepository;
import com.gym.monsterfit.services.interfaces.RutinaServiceInterface;

@Service
public class RutinaService implements RutinaServiceInterface{

	@Autowired
	private RutinaRepository repository;
	
	@Override
	public List<RutinaEntity> getAllRutinaEjercicio() {
		return repository.findAll();
	}

	@Override
	public RutinaEntity getRutinaEjercicioById(RutinaEntity rutina) {
		return repository.findById(rutina.getId()).orElse(null);
	}

	@Override
	public RutinaEntity createRutinaEjercicio(RutinaEntity rutina) {
		return repository.save(rutina);
	}

	@Override
	public RutinaEntity updateRutinaEjercicio(RutinaEntity rutina) {
		return repository.save(rutina);
	}

	@Override
	public void deleteRutinaEjercicio(Integer id) {
		repository.deleteById(id);
	}

}
