package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.HistorialEntity;
public interface HistorialServiceInterface {

	public List<HistorialEntity> getAllHistorial();
	
	public HistorialEntity getHistorialById(HistorialEntity ejercicio);
	
	public HistorialEntity createHistorial(HistorialEntity ejercicio);
	
	public HistorialEntity updateHistorial(HistorialEntity ejercicio);
	
	public void deleteHistorial(Integer id);
}
