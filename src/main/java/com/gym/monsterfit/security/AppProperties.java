package com.gym.monsterfit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/*
 * Clase creada como Bean para poder tomar los valos escritos en application.properties
 */
@Component
public class AppProperties {
    
    @Autowired
    private Environment env;

    public String getTokenSecret(){
        return env.getProperty("tokenSecret");
    }
}
