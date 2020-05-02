package com.patient.treatment.documentation.gui.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.security.SecureRandom;
import java.util.Locale;

@Configuration
public class Config {

    private static final String SALT = "salt-protection";

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("PL", "POLAND"));
        return sessionLocaleResolver;
    }

}
