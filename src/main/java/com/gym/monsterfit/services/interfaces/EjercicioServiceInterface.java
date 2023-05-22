package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.EjercicioEntity;
public interface EjercicioServiceInterface {

	public List<EjercicioEntity> getAllEjercicios();
	
	public EjercicioEntity getEjercicioById(EjercicioEntity ejercicio);
	
	public EjercicioEntity createEjercicio(EjercicioEntity ejercicio);
	
	public EjercicioEntity updateEjercicio(EjercicioEntity ejercicio);
	
	public void deleteEjercicio(Integer id);
}
