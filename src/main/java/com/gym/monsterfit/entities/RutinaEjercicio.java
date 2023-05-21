package com.gym.monsterfit.entities;

import javax.persistence.*;

@Entity
@Table(name = "rutinaEjercicio")
public class RutinaEjercicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "rutina")
	private RutinaEntity rutina;
	
	@ManyToOne
	@JoinColumn(name = "ejercicio_id")
	private EjercicioEntity ejercicio;
	
	@ManyToOne
	@JoinColumn(name = "calendario_id")
	private CalendarioEntity calendario;
	
	private String tiempo;
	private Integer peso;
	private Integer series;
	private Integer repeticiones;
	
	
	
	public RutinaEjercicio(Integer id, RutinaEntity rutina, EjercicioEntity ejercicio, CalendarioEntity calendario, String tiempo,
			Integer peso, Integer series, Integer repeticiones) {
		this.id = id;
		this.rutina = rutina;
		this.ejercicio = ejercicio;
		this.calendario = calendario;
		this.tiempo = tiempo;
		this.peso = peso;
		this.series = series;
		this.repeticiones = repeticiones;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RutinaEntity getRutina() {
		return rutina;
	}
	public void setRutina(RutinaEntity rutina) {
		this.rutina = rutina;
	}
	public EjercicioEntity getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(EjercicioEntity ejercicio) {
		this.ejercicio = ejercicio;
	}
	public CalendarioEntity getCalendario() {
		return calendario;
	}
	public void setCalendario(CalendarioEntity calendario) {
		this.calendario = calendario;
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
	public Integer getSeries() {
		return series;
	}
	public void setSeries(Integer series) {
		this.series = series;
	}
	public Integer getRepeticiones() {
		return repeticiones;
	}
	public void setRepeticiones(Integer repeticiones) {
		this.repeticiones = repeticiones;
	}
	
	
	
	
	
}
