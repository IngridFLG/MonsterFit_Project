package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.TipoEntity;
public interface TipoService {

	public List<TipoEntity> getAllRutina();
	
	public TipoEntity getRutinaById(TipoEntity rutina);
	
	public TipoEntity createRutina(TipoEntity rutina);
	
	public TipoEntity updateRutina(TipoEntity rutina);
	
	public void deleteRutina(Integer id);
}
