package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.HistorialEntity;
public interface HistorialService {

	public List<HistorialEntity> getAllHistorial();
	
	public HistorialEntity getHistorialById(HistorialEntity ejercicio);
	
	public HistorialEntity createHistorial(HistorialEntity ejercicio);
	
	public HistorialEntity updateHistorial(Integer id,HistorialEntity ejercicio);
	
	public void deleteHistorial(Integer id);
}
