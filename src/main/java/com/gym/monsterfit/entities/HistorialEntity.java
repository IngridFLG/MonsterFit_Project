package com.gym.monsterfit.entities;

import java.time.LocalDate;

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
	@JoinColumn(name = "rutina_id")
	private RutinaEntity rutina;
	
	@ManyToOne
	@JoinColumn(name = "miembroGimnasio_id")
	private MiembroEntity miembro;
	
	private Integer series;

	@Column(length = 50)
	private String nombre;

	@Column(length = 50)
	private String tiempo;

	private Float peso;

	private Integer repeticiones;
	
	private LocalDate fecha;
	

}
