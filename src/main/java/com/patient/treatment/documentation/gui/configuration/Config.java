package com.patient.treatment.documentation.gui.configuration;

import com.patient.treatment.documentation.gui.exceptions.UnexpectedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.security.SecureRandom;
import java.util.Locale;

@Configuration
public class Config {

    @Value("${salt.protection}")
    private String salt;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        if (StringUtils.isBlank(salt)) {
            throw new UnexpectedException("salt protection was not set");
        }
        return new BCryptPasswordEncoder(12, new SecureRandom(salt.getBytes()));
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("PL", "POLAND"));
        return sessionLocaleResolver;
    }

}
