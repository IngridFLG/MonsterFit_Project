package com.gym.monsterfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gym.monsterfit.security.AppProperties;

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

    @Bean(name = "springApplicationContext")
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
}
