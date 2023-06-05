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
@Table(name = "ejercicio", indexes = { @Index(columnList = "nombre", name = "index_nombre", unique = true) })
public class EjercicioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, length = 50)
	private String nombre;

	@Column(nullable = false, length = 255)
	private String url;

	@Column(nullable = false, length = 50)
	private String tiempo;

	private Float peso;

	private Integer series;

	private Integer repeticiones;

	@JsonIgnore
	@OneToMany(mappedBy = "ejercicio")
	private List<RutinaEjercicioEntity> ejercicio = new ArrayList<>();

	@Override
	public String toString() {
		return "EjercicioEntity{" + "id=" + id + ", nombre='" + nombre + '\'' + ", peso=" + peso + ", repeticiones="
				+ repeticiones + ", series=" + series + ", tiempo=" + tiempo + ", url='" + url + '\'' + '}';
	}

}
