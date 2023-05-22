package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.CalendarioEntity;


public interface CalendarioServiceInterface {

	public List<CalendarioEntity> getAllCalendarios();
	
	public CalendarioEntity getCalendarioById(CalendarioEntity calendario);
	
	public CalendarioEntity createCalendario(CalendarioEntity calendario);
	
	public CalendarioEntity updateCalendario(CalendarioEntity calendario);
	
	public void deleteCalendario(Integer id);
}
