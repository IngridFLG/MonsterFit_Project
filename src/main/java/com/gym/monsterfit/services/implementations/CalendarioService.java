package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.CalendarioEntity;
import com.gym.monsterfit.repositories.CalendarioRepository;
import com.gym.monsterfit.services.interfaces.CalendarioServiceInterface;

@Service
public class CalendarioService implements CalendarioServiceInterface{
	
	@Autowired
	private CalendarioRepository calendarioRepository;

	@Override
	public List<CalendarioEntity> getAllCalendarios() {
		// TODO Auto-generated method stub
		return calendarioRepository.findAll();
	}

	@Override
	public CalendarioEntity getCalendarioById(CalendarioEntity calendario) {
		// TODO Auto-generated method stub
		return calendarioRepository.findById(calendario.getId()).orElse(null);
	}

	@Override
	public CalendarioEntity createCalendario(CalendarioEntity calendario) {
		// TODO Auto-generated method stub
		return calendarioRepository.save(calendario);
	}

	@Override
	public CalendarioEntity updateCalendario(CalendarioEntity calendario) {
		// TODO Auto-generated method stub
		return calendarioRepository.save(calendario);
	}

	@Override
	public void deleteCalendario(Integer id) {
		calendarioRepository.deleteById(id);
	}

}
