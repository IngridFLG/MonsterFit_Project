package com.gym.monsterfit.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JoinColumn(name = "rutina_id")
	private TipoEntity tipo;
	
	@ManyToOne
	@JoinColumn(name = "ejercicio_id")
	private EjercicioEntity ejercicio;
	
	private Date fecha;

	@JsonIgnore
	@OneToMany(mappedBy = "rutinaEjercicio", cascade = CascadeType.ALL)
    private List<HistorialEntity> historiales = new ArrayList<>();
	
	
}
