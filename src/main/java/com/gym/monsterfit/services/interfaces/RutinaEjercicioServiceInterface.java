package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.RutinaEjercicioEntity;
public interface RutinaEjercicioServiceInterface {

	public List<RutinaEjercicioEntity> getAllRutinaEjercicio();
	
	public RutinaEjercicioEntity getRutinaEjercicioById(RutinaEjercicioEntity rutina);
	
	public RutinaEjercicioEntity createRutinaEjercicio(RutinaEjercicioEntity rutina);
	
	public RutinaEjercicioEntity updateRutinaEjercicio(RutinaEjercicioEntity rutina);
	
	public void deleteRutinaEjercicio(Integer id);
}
