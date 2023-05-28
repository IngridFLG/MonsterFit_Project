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
@Table(name = "rutinaEjercicio")
public class RutinaEjercicioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "rutina")
	private TipoEntity tipo;
	
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
	
	
}
