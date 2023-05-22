package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.TipoEntity;
import com.gym.monsterfit.repositories.TipoRepository;
import com.gym.monsterfit.services.interfaces.TipoServiceInterface;

@Service
public class TipoService implements TipoServiceInterface{

	@Autowired
	private TipoRepository repository;
	
	@Override
	public List<TipoEntity> getAllRutina() {
		return repository.findAll();
	}

	@Override
	public TipoEntity getRutinaById(TipoEntity rutina) {
		// TODO Auto-generated method stub
		return repository.findById(rutina.getId()).orElse(null);
	}

	@Override
	public TipoEntity createRutina(TipoEntity rutina) {
		return repository.save(rutina);
	}

	@Override
	public TipoEntity updateRutina(TipoEntity rutina) {
		return repository.save(rutina);
	}

	@Override
	public void deleteRutina(Integer id) {
		repository.deleteById(id);
	}

}
