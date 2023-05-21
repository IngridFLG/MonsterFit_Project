package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.RutinaEjercicio;
public interface RutinaEjercicioService {

	public List<RutinaEjercicio> getAllRutinaEjercicio();
	
	public RutinaEjercicio getRutinaEjercicioById(RutinaEjercicio rutina);
	
	public RutinaEjercicio createRutinaEjercicio(RutinaEjercicio rutina);
	
	public RutinaEjercicio updateRutinaEjercicio(Integer id,RutinaEjercicio rutina);
	
	public void deleteRutinaEjercicio(Integer id);
}
