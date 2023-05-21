package com.gym.monsterfit.entities;

import javax.persistence.*;
@Entity
@Table(name = "ejercicio")
public class Ejercicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name = "video_id")
	private Video video;
	
	

	public Ejercicio(Integer id, String descripcion, Video video) {
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

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	
}
