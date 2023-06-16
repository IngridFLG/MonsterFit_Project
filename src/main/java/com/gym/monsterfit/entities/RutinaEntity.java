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
public class RutinaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String nombre;

	@JsonIgnore
	@OneToMany(mappedBy = "rutina")
    private List<RutinaEjercicioEntity> rutina= new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "rutina")
    private List<HistorialEntity> rutinaHistorial= new ArrayList<>();
	
	
	
}
