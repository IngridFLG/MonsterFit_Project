package com.gym.monsterfit.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historialRutina")
public class HistorialEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "rutinaEjercicio_id")
	private RutinaEjercicioEntity rutinaEjercicio;
	
	@ManyToOne
	@JoinColumn(name = "miembroGimnasio_id")
	private MiembroEntity miembro;
	
	private Integer series;
	private String tiempo;
	private Integer peso;
	private Integer repeticiones;
	
	

}
