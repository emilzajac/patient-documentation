package com.patient.treatment.documentation.gui.configuration;

import com.patient.treatment.documentation.gui.service.security.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserPrincipalDetailService userPrincipalDetailService;

    private static final String[] PERMITTED_LINKS = {"/api/login"};

    @Autowired
    public SecurityConfig(BCryptPasswordEncoder passwordEncoder,
                          UserPrincipalDetailService userPrincipalDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.userPrincipalDetailService = userPrincipalDetailService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                .authorizeRequests()
                .antMatchers(PERMITTED_LINKS).permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout", "POST"))
                .and()
                .formLogin().loginPage("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipalDetailService).passwordEncoder(passwordEncoder);
//        auth.inMemoryAuthentication().withUser("u").password(passwordEncoder.encode("u")).roles("ADMINISTRATOR"); //This is in-memory authentication
    }

}
