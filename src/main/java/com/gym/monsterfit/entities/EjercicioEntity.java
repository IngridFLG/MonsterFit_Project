package com.gym.monsterfit.entities;

import java.util.ArrayList;
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
@Table(name = "ejercicio", indexes = {@Index(columnList = "nombre", name = "index_nombre", unique = true) })
public class EjercicioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String nombre;
	
	private String url;
	
	private String tiempo;

	private Integer peso;

	private Integer series;

	private Integer repeticiones;

	@JsonIgnore
	@ManyToMany(mappedBy = "ejercicios", cascade = CascadeType.ALL)
    private List<RutinaEjercicioEntity> rutinaEjercicios = new ArrayList<>();
	


}
