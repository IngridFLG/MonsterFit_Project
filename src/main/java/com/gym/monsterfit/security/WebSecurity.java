package com.gym.monsterfit.security;


import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gym.monsterfit.services.implementations.JWTServiceImple;
import com.gym.monsterfit.services.interfaces.UsuarioService;


@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter   {
    
  
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    JWTServiceImple jwtService;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/usuario/login","/usuario/crear").permitAll()
            .anyRequest().authenticated().and()
            .addFilter((Filter) getAuthenticationFilter())
            .addFilter((Filter) new AuthorizationFilter(authenticationManager(), jwtService)).sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder);
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(),jwtService);
        filter.setFilterProcessesUrl("/login");
        return filter;
    }
}
