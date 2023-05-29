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
@Table(name = "rutina")
public class TipoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre; //Nombre del Tipo de rutina
	
	@JsonIgnore
	@OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<RutinaEjercicioEntity> rutinaEjercicios = new ArrayList<>();
	
}
