package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.MiembroEntity;
public interface MiembroService {

	public List<MiembroEntity> getAllMiembro();
	
	public MiembroEntity getMiembroById(MiembroEntity miembro);
	
	public MiembroEntity createMiembro(MiembroEntity miembro);
	
	public MiembroEntity updateMiembro(Integer id,MiembroEntity miembro);
	
	public void deleteMiembro(Integer id);
}
