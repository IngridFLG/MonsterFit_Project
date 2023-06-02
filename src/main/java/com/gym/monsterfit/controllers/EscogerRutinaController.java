package com.gym.monsterfit.controllers;

import com.gym.monsterfit.entities.TipoEntity;
import com.gym.monsterfit.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EscogerRutinaController {

    @Autowired
    TipoRepository tipoRepository;

    @GetMapping("/tipoRutina")
    public String mostrarFormulario(Model model) {
        List<TipoEntity> tiposRutina = tipoRepository.findAll();
        model.addAttribute("tiposRutina", tiposRutina);
        return "seleccionar-rutina";
    }


}
