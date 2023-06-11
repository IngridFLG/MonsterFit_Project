package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import com.gym.monsterfit.entities.RutinaEntity;
import com.gym.monsterfit.repositories.EjercicioRepository;
import com.gym.monsterfit.repositories.RutinaEjercicioRepository;
import com.gym.monsterfit.repositories.RutinaRepository;
import com.gym.monsterfit.services.implementations.RutinaEjercicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/elegirRutina")
public class EscogerRutinaUserController {

	@Autowired
	RutinaRepository rutinaRepository;

	@Autowired
	EjercicioRepository ejercicioRepository;

	@Autowired
	RutinaEjercicioService rutinaEjercicioService;

	@Autowired
	RutinaEjercicioRepository rutinaEjercicioRepository;

	@GetMapping
	public String seleccionarRutina(Model model) {
		List<RutinaEntity> tiposRutina = rutinaRepository.findAll();

		RutinaEntity rutina1 = tiposRutina.get(0);
		RutinaEntity rutina2 = tiposRutina.get(1);
		RutinaEntity rutina3 = tiposRutina.get(2);

		model.addAttribute("rutina1", rutina1);
		model.addAttribute("rutina2", rutina2);
		model.addAttribute("rutina3", rutina3);

		return "cliente/chooseRoutine";
	}
	@GetMapping("/rutinaUser")
	public String calendarioRutina() {
		
		return "cliente/ejerciciosRutinaMes";
	}
	/*
	@GetMapping
	public String mostrarFormulario(Model model) {
		List<RutinaEntity> tiposRutina = rutinaRepository.findAll();

		model.addAttribute("tiposRutina", tiposRutina);
		return "admin/asignarRutina";
	}

	@PostMapping
	public String procesarSeleccionTipoRutina(@RequestParam("selectedOption") String rutina,
			@RequestParam("inputDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha1, Model modal) {
		RutinaEntity rutinaEntity = rutinaRepository.getReferenceById(Integer.parseInt(rutina));

		List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
		List<RutinaEjercicioEntity> rutinaEjercicios = rutinaEjercicioRepository
				.findByRutinaIdAndFecha(rutinaEntity.getId(), fecha1);
		modal.addAttribute("rutinaEjercicios", rutinaEjercicios);

		modal.addAttribute("rutinaEntity", rutinaEntity);
		modal.addAttribute("fecha", fecha1);

		modal.addAttribute("ejercicios", ejercicios);

		return "admin/agregarEjercicioRutina";
	}

	@GetMapping("/borrar/{id}")
	public String eliminarCategoria(@PathVariable("id") Integer id, Model model) {

		RutinaEjercicioEntity rutinaEjercicioEntity = rutinaEjercicioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Rutina ejercicio no encontrada con id: " + id));
		rutinaEjercicioRepository.delete(rutinaEjercicioEntity);

		List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
		List<RutinaEjercicioEntity> rutinaEjercicios = rutinaEjercicioRepository
				.findByRutinaIdAndFecha(rutinaEjercicioEntity.getRutina().getId(), rutinaEjercicioEntity.getFecha());
		model.addAttribute("rutinaEjercicios", rutinaEjercicios);

		model.addAttribute("rutinaEntity", rutinaEjercicioEntity.getRutina());
		model.addAttribute("fecha", rutinaEjercicioEntity.getFecha());

		model.addAttribute("ejercicios", ejercicios);
		return "admin/agregarEjercicioRutina";
	}

	@PostMapping("/guardar")
	public String procesarFormRutina(@RequestParam("inputDate") String fechaString,
			@RequestParam("rutinaId") String rutinaIdString, @RequestParam("selectedOption") Integer ejercicio,
			Model model) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(fechaString, formatter);

		Integer id = Integer.parseInt(rutinaIdString);
		RutinaEntity rutina = rutinaRepository.findById(id).get();
		EjercicioEntity ejercicioEntity = ejercicioRepository.findById(ejercicio).get();

		// Se crea para realizar la relacion entre ejercicio y rutina
		RutinaEjercicioEntity rutinaEjercicioEntity = new RutinaEjercicioEntity();
		rutinaEjercicioEntity.setFecha(fecha);
		rutinaEjercicioEntity.setRutina(rutina);
		rutinaEjercicioEntity.setEjercicio(ejercicioEntity);

		// vainas que toca hacer para no totear el front
		List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
		List<RutinaEjercicioEntity> rutinaEjercicios = rutinaEjercicioRepository
				.findByRutinaIdAndFecha(rutinaEjercicioEntity.getRutina().getId(), rutinaEjercicioEntity.getFecha());
		model.addAttribute("rutinaEjercicios", rutinaEjercicios);
		model.addAttribute("rutinaEntity", rutina);
		model.addAttribute("fecha", rutinaEjercicioEntity.getFecha());
		model.addAttribute("ejercicios", ejercicios);

		// validacion para comprobar que el ejercicio no este en la rutina
		boolean ejercicioDuplicado = rutinaEjercicioRepository.existsByRutinaAndEjercicioAndFecha(rutina,
				ejercicioEntity, fecha);
		if (ejercicioDuplicado) {

			model.addAttribute("nombreDuplicado", true);
			return "admin/agregarEjercicioRutina";
		}

		rutinaEjercicioRepository.save(rutinaEjercicioEntity);
		rutinaEjercicios.add(rutinaEjercicioEntity);
		model.addAttribute("rutinaEjercicios", rutinaEjercicios);
		model.addAttribute("nombreDuplicado", false);
		return "admin/agregarEjercicioRutina";
	}
	*/
}
