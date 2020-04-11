package com.patient.treatment.documentation.gui.configuration;

import com.patient.treatment.documentation.gui.converter.SecurityAnswerConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
@PropertySource("classpath:/errors_pl.properties")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Value("${error.title.access.denied}")
    private String title;

    @Value("${error.message.access.denied}")
    private String message;

    private SecurityAnswerConverter securityAnswerConverter;

    public CustomAccessDeniedHandler(SecurityAnswerConverter securityAnswerConverter) {
        this.securityAnswerConverter = securityAnswerConverter;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        log.debug("Błąd - brak dostępu dla zautoryzowanego użytkownika: " + httpServletRequest.getServletPath());
        final HttpStatus status = HttpStatus.FORBIDDEN;
        httpServletResponse.setStatus(status.value());
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(securityAnswerConverter.convertToJsonAnswer(title, message, status));
    }

}
