package com.gym.monsterfit.entities;

import javax.persistence.*;

@Entity
@Table(name = "rutina")
public class TipoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre; //Nombre del Tipo de rutina
	
	
	public TipoEntity(Integer id, String tipo) {
		this.id = id;
		this.nombre = tipo;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTipo() {
		return nombre;
	}
	
	public void setTipo(String tipo) {
		this.nombre = tipo;
	}
	
	
}
