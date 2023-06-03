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
@Table(name = "miembroGimnasio")
public class MiembroEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String nombre;

	private Integer edad;

	@Column(nullable = false, length = 1)
	private String sexo;

	private Integer altura;
	
	private Double peso;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private UsuarioEntity usuario;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "miembro", cascade = CascadeType.ALL)
    private List<HistorialEntity> historiales = new ArrayList<>();
}
