package com.gym.monsterfit.entities;

import javax.persistence.*;

@Entity
@Table(name = "video")
public class VideoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String url;
	private String nombre;
	
	public VideoEntity(Integer id, String url, String nombre) {
		this.id = id;
		this.url = url;
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
