package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.EjercicioEntity;
import com.gym.monsterfit.entities.MiembroEntity;
import com.gym.monsterfit.entities.RutinaEjercicioEntity;
import com.gym.monsterfit.entities.RutinaEntity;
import com.gym.monsterfit.repositories.EjercicioRepository;
import com.gym.monsterfit.repositories.MiembroRepository;
import com.gym.monsterfit.repositories.RutinaEjercicioRepository;
import com.gym.monsterfit.repositories.RutinaRepository;
import com.gym.monsterfit.services.implementations.RutinaEjercicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

	@Autowired
	MiembroRepository miembroRepository;

	@GetMapping(value = "/{miembroId}")
	public String seleccionarRutina(Model model, @PathVariable Integer miembroId) {
		List<RutinaEntity> tiposRutina = rutinaRepository.findAll();
		MiembroEntity miembro = miembroRepository.findByUsuario_Id(miembroId);

		RutinaEntity rutina1 = tiposRutina.get(0);
		RutinaEntity rutina2 = tiposRutina.get(1);
		RutinaEntity rutina3 = tiposRutina.get(2);

		model.addAttribute("rutina1", rutina1);
		model.addAttribute("rutina2", rutina2);
		model.addAttribute("rutina3", rutina3);
		model.addAttribute("miembroEntity", miembro);

		return "cliente/chooseRoutine";
	}

	@GetMapping("/rutinaUser/{rutinaId}")
	public String calendarioRutina(Model model, @PathVariable Integer rutinaId) {
		RutinaEntity rutina = rutinaRepository.findById(rutinaId).orElse(null);
		List<RutinaEjercicioEntity> dias = rutinaEjercicioRepository.findByRutinaId(rutinaId).stream()
				.collect(Collectors.collectingAndThen(
						Collectors.toCollection(
								() -> new TreeSet<>(Comparator.comparing(RutinaEjercicioEntity::getFecha))),
						ArrayList::new));

		LocalDate now = LocalDate.now();
		int currentMonth = now.getMonthValue();
		int currentYear = now.getYear();

		List<Pair<String, String>> filteredDias = dias.stream()
				.filter(r -> {
					LocalDate ejercicioFecha = r.getFecha();
					return ejercicioFecha.getMonthValue() == currentMonth && ejercicioFecha.getYear() == currentYear;
				})
				.map(r -> {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'del' yyyy",
							new Locale("es", "ES"));
					DateTimeFormatter simpleFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");

					return Pair.of(r.getFecha().format(simpleFormatter), r.getFecha().format(formatter));
				})
				.collect(Collectors.toList());

		model.addAttribute("rutina", rutina);
		model.addAttribute("dias", filteredDias);

		return "cliente/ejerciciosRutinaMes";
	}

	@GetMapping("/rutinaUser/{rutinaId}/{fecha}")
	public String calendarioRutinaDia(Model model, @PathVariable Integer rutinaId, @PathVariable String fecha) {
		RutinaEntity rutina = rutinaRepository.findById(rutinaId).orElse(null);
		List<RutinaEjercicioEntity> dias = rutinaEjercicioRepository.findByRutinaId(rutinaId);

		DateTimeFormatter simpleFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'del' yyyy",
							new Locale("es", "ES"));
		List<EjercicioEntity> ejercicios = dias.stream()
				.filter(r -> {
					LocalDate ejercicioFecha = r.getFecha(); 

					return LocalDate.parse(fecha, simpleFormatter).isEqual(ejercicioFecha);
				})
				.map(r -> r.getEjercicio())
				.collect(Collectors.toList());

		model.addAttribute("ejercicios", ejercicios);


		model.addAttribute("fechaActual", LocalDate.parse(fecha, simpleFormatter).format(formatter));

		return "cliente/ejerciciosRutinaDia";
	}

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

	@GetMapping(value = "ejercicio/{ejercicioId}")
	public String verEjercicio(Model model, @PathVariable Integer ejercicioId) {
		EjercicioEntity ejercicio = ejercicioRepository.findById(ejercicioId).orElse(null);

		model.addAttribute("ejercicio", ejercicio);

		return "cliente/ejercicioDetallado";
	}

	// @PostMapping("/guardar")
	// public String procesarFormRutina(@RequestParam("inputDate") String
	// fechaString,
	// @RequestParam("rutinaId") String rutinaIdString,
	// @RequestParam("selectedOption") Integer ejercicio,
	// Model model) {

	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// LocalDate fecha = LocalDate.parse(fechaString, formatter);

	// Integer id = Integer.parseInt(rutinaIdString);
	// RutinaEntity rutina = rutinaRepository.findById(id).get();
	// EjercicioEntity ejercicioEntity =
	// ejercicioRepository.findById(ejercicio).get();

	// Se crea para realizar la relacion entre ejercicio y rutina
	// RutinaEjercicioEntity rutinaEjercicioEntity = new RutinaEjercicioEntity();
	// rutinaEjercicioEntity.setFecha(fecha);
	// rutinaEjercicioEntity.setRutina(rutina);
	// rutinaEjercicioEntity.setEjercicio(ejercicioEntity);

	// vainas que toca hacer para no totear el front
	// List<EjercicioEntity> ejercicios = ejercicioRepository.findAll();
	// List<RutinaEjercicioEntity> rutinaEjercicios = rutinaEjercicioRepository
	// .findByRutinaIdAndFecha(rutinaEjercicioEntity.getRutina().getId(),
	// rutinaEjercicioEntity.getFecha());
	// model.addAttribute("rutinaEjercicios", rutinaEjercicios);
	// model.addAttribute("rutinaEntity", rutina);
	// model.addAttribute("fecha", rutinaEjercicioEntity.getFecha());
	// model.addAttribute("ejercicios", ejercicios);

	// validacion para comprobar que el ejercicio no este en la rutina
	// boolean ejercicioDuplicado =
	// rutinaEjercicioRepository.existsByRutinaAndEjercicioAndFecha(rutina,
	// ejercicioEntity, fecha);
	// if (ejercicioDuplicado) {

	// model.addAttribute("nombreDuplicado", true);
	// return "admin/agregarEjercicioRutina";
	// }

	// rutinaEjercicioRepository.save(rutinaEjercicioEntity);
	// rutinaEjercicios.add(rutinaEjercicioEntity);
	// model.addAttribute("rutinaEjercicios", rutinaEjercicios);
	// model.addAttribute("nombreDuplicado", false);
	// return "admin/agregarEjercicioRutina";
	// }

}
