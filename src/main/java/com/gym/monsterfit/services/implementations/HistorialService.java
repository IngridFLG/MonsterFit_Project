package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.HistorialEntity;
import com.gym.monsterfit.repositories.HistorialRepository;
import com.gym.monsterfit.services.interfaces.HistorialServiceInterface;

@Service
public class HistorialService implements HistorialServiceInterface{
	
	@Autowired
	private HistorialRepository repository;
	
	@Override
	public List<HistorialEntity> getAllHistorial() {
		return repository.findAll();
	}

	@Override
	public HistorialEntity getHistorialById(HistorialEntity historial) {
		return repository.findById(historial.getId()).orElse(null);
	}

	@Override
	public HistorialEntity createHistorial(HistorialEntity historial) {
		return repository.save(historial);
	}

	@Override
	public HistorialEntity updateHistorial(HistorialEntity historial) {
		return repository.save(historial);
	}

	@Override
	public void deleteHistorial(Integer id) {
		repository.deleteById(id);
	}

}
