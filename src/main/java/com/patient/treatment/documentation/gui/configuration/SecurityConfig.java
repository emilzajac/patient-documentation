package com.patient.treatment.documentation.gui.configuration;

import com.patient.treatment.documentation.gui.model.security.jwt.AuthTokenFilter;
import com.patient.treatment.documentation.gui.service.security.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserPrincipalDetailService userPrincipalDetailService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomStatusEntryPoint customStatusEntryPoint;
    private final AuthTokenFilter authTokenFilter;

    private static final String[] PERMITTED_LINKS = {"/api/users/register", "/confirm/account", "/api/login", "/index.html", "/"};
    private static final String HEADER_CONTENT_SECURITY_POLICY =
            "default-src 'self'; font-src 'self' data: http://fonts.gstatic.com; img-src 'self' data:; " +
                    "script-src * data: https://ssl.gstatic.com 'unsafe-inline' 'unsafe-eval';" +
                    "style-src 'self' 'unsafe-inline' http://fonts.googleapis.com https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css";

    public SecurityConfig(BCryptPasswordEncoder passwordEncoder,
                          UserPrincipalDetailService userPrincipalDetailService,
                          CustomAccessDeniedHandler customAccessDeniedHandler,
                          CustomStatusEntryPoint customStatusEntryPoint,
                          AuthTokenFilter authTokenFilter) {
        this.passwordEncoder = passwordEncoder;
        this.userPrincipalDetailService = userPrincipalDetailService;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customStatusEntryPoint = customStatusEntryPoint;
        this.authTokenFilter = authTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.headers()
                .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP", HEADER_CONTENT_SECURITY_POLICY))
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", HEADER_CONTENT_SECURITY_POLICY))
                .contentSecurityPolicy(HEADER_CONTENT_SECURITY_POLICY);

        httpSecurity.headers().httpStrictTransportSecurity()
                .includeSubDomains(true)
                .maxAgeInSeconds(31536000);

        httpSecurity.headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.DENY));

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionFixation().migrateSession();

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(customStatusEntryPoint)
                .and()

                .authorizeRequests()
                .antMatchers(PERMITTED_LINKS).permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()

                .csrf()
                .ignoringAntMatchers("/api/login", "/api/users/register", "/confirm/account")
                .csrfTokenRepository(getCsrfTokenRepository())
                .and()

                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(successLogoutHandler())
                .invalidateHttpSession(true)
                .deleteCookies("DOCUMENTATIONCOOKIE")
                .clearAuthentication(true)
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(customStatusEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);

        httpSecurity.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private CsrfTokenRepository getCsrfTokenRepository() {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        return tokenRepository;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipalDetailService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public LogoutSuccessHandler successLogoutHandler() {
        return new SuccessLogoutHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
