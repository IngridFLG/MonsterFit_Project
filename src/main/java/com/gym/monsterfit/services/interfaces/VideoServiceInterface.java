package com.gym.monsterfit.services.interfaces;

import java.util.List;
import com.gym.monsterfit.entities.VideoEntity;

public interface VideoServiceInterface {

	public List<VideoEntity> getAllUsuario();
	
	public VideoEntity getVideoById(VideoEntity video);
	
	public VideoEntity createVideo(VideoEntity video);
	
	public VideoEntity updateVideo(VideoEntity video);
	
	public void deleteVideo(Integer id);
}
