package com.gym.monsterfit.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gym.monsterfit.entities.RutinaEntity;

public interface RutinaRepository extends JpaRepository<RutinaEntity, Integer>{
	Optional<RutinaEntity> findById(Long id);
}
