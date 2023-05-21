package com.gym.monsterfit.entities;

import javax.persistence.*;

@Entity
@Table(name = "rutina")
public class RutinaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tipo;
	
	
	public RutinaEntity(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
