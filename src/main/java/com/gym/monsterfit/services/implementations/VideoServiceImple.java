package com.gym.monsterfit.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.monsterfit.entities.VideoEntity;
import com.gym.monsterfit.repositories.VideoRepository;
import com.gym.monsterfit.services.interfaces.VideoService;

@Service
public class VideoServiceImple implements VideoService{
	
	@Autowired
	private VideoRepository repository;

	@Override
	public List<VideoEntity> getAllUsuario() {
		return repository.findAll();
	}

	@Override
	public VideoEntity getVideoById(VideoEntity video) {
		return repository.findById(video.getId()).orElse(null);
	}

	@Override
	public VideoEntity createVideo(VideoEntity video) {
		return repository.save(video);
	}

	@Override
	public VideoEntity updateVideo(VideoEntity video) {
		return repository.save(video);
	}

	@Override
	public void deleteVideo(Integer id) {
		repository.deleteById(id);
	}

}
