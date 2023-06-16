package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.*;
import com.gym.monsterfit.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
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
	private RutinaRepository rutinaRepository;

	@Autowired
	private EjercicioRepository ejercicioRepository;


	@Autowired
	private RutinaEjercicioRepository rutinaEjercicioRepository;

	@Autowired
	private MiembroRepository miembroRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private HistorialRepository historialRepository;

	@GetMapping(value = "/{usuarioId}")
	public String seleccionarRutina(Model model, @PathVariable Integer usuarioId) {
		List<RutinaEntity> tiposRutina = rutinaRepository.findAll();
		MiembroEntity miembro = miembroRepository.findByUsuario_Id(usuarioId);

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
	public String calendarioRutina(Model model, @PathVariable Integer rutinaId, Principal principal) {

		String username = principal.getName();
		UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
		MiembroEntity miembroEntity = miembroRepository.findByUsuario_Id(usuarioEntity.getId());
		model.addAttribute("miembroEntity", miembroEntity);

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
	public String calendarioRutinaDia(Model model, @PathVariable Integer rutinaId, @PathVariable String fecha, Principal principal) {

		String username = principal.getName();
		UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
		MiembroEntity miembroEntity = miembroRepository.findByUsuario_Id(usuarioEntity.getId());
		model.addAttribute("miembroEntity", miembroEntity);

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
		model.addAttribute("rutina", rutina);

		model.addAttribute("fechaActual", LocalDate.parse(fecha, simpleFormatter).format(formatter));

		return "cliente/ejerciciosRutinaDia";
	}

	@GetMapping(value = "ejercicio/{ejercicioId}/rutina/{rutinaId}")
	public String verEjercicio(Model model, @PathVariable Integer ejercicioId, @PathVariable("rutinaId") Integer rutinaId, Principal principal) {
		String username = principal.getName();
		UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
		MiembroEntity miembroEntity = miembroRepository.findByUsuario_Id(usuarioEntity.getId());
		model.addAttribute("miembroEntity", miembroEntity);

		EjercicioEntity ejercicio = ejercicioRepository.findById(ejercicioId).orElse(null);
		RutinaEntity rutinaEntity = rutinaRepository.findById(rutinaId).get();
		model.addAttribute("rutina", rutinaEntity);
		model.addAttribute("ejercicio", ejercicio);
		System.out.println(rutinaEntity.getNombre());
		return "cliente/ejercicioDetallado";
	}

	@PostMapping("/guardarHistorial")
	public String guardarHistorial(@ModelAttribute("ejercicio") EjercicioEntity ejercicioEntity, Principal principal, Model model, @ModelAttribute("rutinaId") Integer rutinaId){

		String username = principal.getName();
		UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
		MiembroEntity miembroEntity = miembroRepository.findByUsuario_Id(usuarioEntity.getId());
		model.addAttribute("miembroEntity", miembroEntity);
		RutinaEntity rutinaEntity = rutinaRepository.findById(rutinaId).get();
		HistorialEntity historialEntity = new HistorialEntity();
		LocalDate fecha = LocalDate.now();
		historialEntity.setFecha(fecha);
		historialEntity.setMiembro(miembroEntity);
		historialEntity.setPeso(ejercicioEntity.getPeso());
		historialEntity.setSeries(ejercicioEntity.getSeries());
		historialEntity.setRepeticiones(ejercicioEntity.getRepeticiones());
		historialEntity.setTiempo(ejercicioEntity.getTiempo());
		historialEntity.setRutina(rutinaEntity);
		historialRepository.save(historialEntity);
		model.addAttribute("exito", true);
		model.addAttribute("rutina", rutinaEntity);
		return "redirect:/elegirRutina/ejercicio/" + ejercicioEntity.getId() + "/rutina/" + rutinaEntity.getId() +"?exito=true"; 
	}


	@GetMapping("/verHistorial/{rutinaId}")
	public String verHistorial(Principal principal, Model model, @PathVariable("rutinaId") Integer rutinaId){
		String username = principal.getName();
		UsuarioEntity usuarioEntity= usuarioRepository.findByEmail(username);
		MiembroEntity miembroEntity = miembroRepository.findByUsuario_Id(usuarioEntity.getId());
		model.addAttribute("miembroEntity", miembroEntity);
		RutinaEntity rutinaEntity = rutinaRepository.findById(rutinaId).get();
		List<HistorialEntity> historial = historialRepository.findByMiembroAndRutina(miembroEntity, rutinaEntity);
		model.addAttribute("historial", historial);
		model.addAttribute("rutina", rutinaEntity);
		return "/cliente/verHistorial";
	}

	
}
