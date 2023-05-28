package com.gym.monsterfit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

	
	
	@GetMapping("/")
	public String registrarUsuario() {
		return "index";
	}
	
	@GetMapping("/login_view")
    public String showLoginPage() {
        return "login";
    }

    

}