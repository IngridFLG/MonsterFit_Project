package com.gym.monsterfit.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "calendario")
public class CalendarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer dia;
	private String mes;
	private Integer ano;
	
	
	@JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "calendario")
    private List<RutinaEjercicioEntity> rutinas = new ArrayList<>();
	
}
