package com.patient.treatment.documentation.gui.configuration;

import com.patient.treatment.documentation.gui.service.security.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserPrincipalDetailService userPrincipalDetailService;

    @Autowired
    public SecurityConfig(BCryptPasswordEncoder passwordEncoder,
                          UserPrincipalDetailService userPrincipalDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.userPrincipalDetailService = userPrincipalDetailService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/api/users").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable().headers().frameOptions().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipalDetailService).passwordEncoder(passwordEncoder);
//        auth.inMemoryAuthentication().withUser("u").password(passwordEncoder.encode("u")).roles("ADMINISTRATOR"); //This is in-memory authentication
    }


}
