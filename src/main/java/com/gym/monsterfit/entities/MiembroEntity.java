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
@Table(name = "miembroGimnasio")
public class MiembroEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private Integer edad;
	private String sexo;
	private Integer altura;
	private Double peso;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuario;	
	
}
