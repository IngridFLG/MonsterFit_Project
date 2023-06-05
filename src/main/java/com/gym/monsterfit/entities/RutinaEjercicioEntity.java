package com.gym.monsterfit.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rutinaEjercicio")
public class RutinaEjercicioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rutina_id")
    private RutinaEntity rutina;

    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private EjercicioEntity ejercicio;

    @JsonIgnore
    @OneToMany(mappedBy = "rutinaEjercico")
    private List<HistorialEntity> historial = new ArrayList<>();

    private LocalDate fecha;
}
