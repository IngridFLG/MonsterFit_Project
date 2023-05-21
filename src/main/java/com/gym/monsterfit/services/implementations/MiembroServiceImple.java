package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.MiembroEntity;
import com.gym.monsterfit.repositories.MiembroRepository;
import com.gym.monsterfit.services.interfaces.MiembroService;

@Service
public class MiembroServiceImple implements MiembroService{

	@Autowired
	private MiembroRepository repository;
	
	@Override
	public List<MiembroEntity> getAllMiembro() {
		return repository.findAll();
	}

	@Override
	public MiembroEntity getMiembroById(MiembroEntity miembro) {
		return repository.findById(miembro.getId()).orElse(null);
	}

	@Override
	public MiembroEntity createMiembro(MiembroEntity miembro) {
		return repository.save(miembro);
	}

	@Override
	public MiembroEntity updateMiembro(MiembroEntity miembro) {
		return repository.save(miembro);
	}

	@Override
	public void deleteMiembro(Integer id) {
		repository.deleteById(id);
	}

}
