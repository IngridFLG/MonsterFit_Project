package com.gym.monsterfit.entities;

import javax.persistence.*;

@Entity
@Table(name = "historialRutina")
public class HistorialEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "rutinaEjercicio")
	private RutinaEjercicioEntity rutinaEjercicio;
	
	@ManyToOne
	@JoinColumn(name = "miembroGimnasio")
	private MiembroEntity miembro;
	
	private Integer series;
	private String tiempo;
	private Integer peso;
	private Integer repeticiones;
	
	
	
	
	public HistorialEntity(Integer id, RutinaEjercicioEntity rutinaEjercicio, MiembroEntity miembro, Integer series, String tiempo,
			Integer peso, Integer repeticiones) {
		super();
		this.id = id;
		this.rutinaEjercicio = rutinaEjercicio;
		this.miembro = miembro;
		this.series = series;
		this.tiempo = tiempo;
		this.peso = peso;
		this.repeticiones = repeticiones;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RutinaEjercicioEntity getRutinaEjercicio() {
		return rutinaEjercicio;
	}
	public void setRutinaEjercicio(RutinaEjercicioEntity rutinaEjercicio) {
		this.rutinaEjercicio = rutinaEjercicio;
	}
	public MiembroEntity getMiembro() {
		return miembro;
	}
	public void setMiembro(MiembroEntity miembro) {
		this.miembro = miembro;
	}
	public Integer getSeries() {
		return series;
	}
	public void setSeries(Integer series) {
		this.series = series;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	public Integer getRepeticiones() {
		return repeticiones;
	}
	public void setRepeticiones(Integer repeticiones) {
		this.repeticiones = repeticiones;
	}
	
	

}
