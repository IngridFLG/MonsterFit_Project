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
@Table(name = "ejercicio")
public class EjercicioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;
	
	private String url;
	
	private String tiempo;

	private Integer peso;

	private Integer series;

	private Integer repeticiones;

	@JsonIgnore
	@OneToMany(mappedBy = "ejercicio", cascade = CascadeType.ALL)
    private List<RutinaEjercicioEntity> rutinaEjercicios = new ArrayList<>();
	


}
