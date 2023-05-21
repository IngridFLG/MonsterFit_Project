package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.RutinaEntity;
public interface RutinaService {

	public List<RutinaEntity> getAllRutina();
	
	public RutinaEntity getRutinaById(RutinaEntity rutina);
	
	public RutinaEntity createRutina(RutinaEntity rutina);
	
	public RutinaEntity updateRutina(Integer id,RutinaEntity rutina);
	
	public void deleteRutina(Integer id);
}
