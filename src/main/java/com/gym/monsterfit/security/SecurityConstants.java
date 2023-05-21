package com.gym.monsterfit.security;

import com.gym.monsterfit.SpringApplicationContext;

public class SecurityConstants {
    
    public static final long EXPIRATION_DATE = 3600000; //1 HORA
    public static final String TOKEN_PREFIX = "YoMeCompreUn47 ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
