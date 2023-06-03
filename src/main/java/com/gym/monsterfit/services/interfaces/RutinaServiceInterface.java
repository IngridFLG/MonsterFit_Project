package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.RutinaEntity;
public interface RutinaServiceInterface {

	public List<RutinaEntity> getAllRutinaEjercicio();
	
	public RutinaEntity getRutinaEjercicioById(RutinaEntity rutina);
	
	public RutinaEntity createRutinaEjercicio(RutinaEntity rutina);
	
	public RutinaEntity updateRutinaEjercicio(RutinaEntity rutina);
	
	public void deleteRutinaEjercicio(Integer id);
}
