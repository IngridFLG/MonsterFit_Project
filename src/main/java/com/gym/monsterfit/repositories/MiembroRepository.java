package com.gym.monsterfit.repositories;
import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gym.monsterfit.entities.MiembroEntity;

public interface MiembroRepository extends JpaRepository<MiembroEntity, Integer>{

	MiembroEntity findByUsuario_Id(Integer usuario_id);

	}
