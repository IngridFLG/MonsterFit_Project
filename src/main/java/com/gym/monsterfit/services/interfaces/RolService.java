package com.gym.monsterfit.services.interfaces;


import java.util.List;

import com.gym.monsterfit.entities.RolEntity;
public interface RolService {

	public List<RolEntity> getAllMiembro();
	
	public RolEntity getRolById(RolEntity miembro);
	
	public RolEntity createRol(RolEntity miembro);
	
	public RolEntity updateRol(Integer id,RolEntity miembro);
	
	public void deleteRol(Integer id);
}
