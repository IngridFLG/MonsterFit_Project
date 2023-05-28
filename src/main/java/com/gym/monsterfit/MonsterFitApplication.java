package com.gym.monsterfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class MonsterFitApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonsterFitApplication.class, args);
        System.out.println("Compila");
    }

    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

   
}
