package com.gym.monsterfit.entities;

import javax.persistence.*;
@Entity
@Table(name = "ejercicio")
public class EjercicioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name = "video_id")
	private VideoEntity video;
	
	

	public EjercicioEntity(Integer id, String descripcion, VideoEntity video) {
		this.id = id;
		this.descripcion = descripcion;
		this.video = video;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public VideoEntity getVideo() {
		return video;
	}

	public void setVideo(VideoEntity video) {
		this.video = video;
	}
	
	
}
