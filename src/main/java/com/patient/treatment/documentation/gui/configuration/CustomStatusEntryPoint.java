package com.patient.treatment.documentation.gui.configuration;

import com.patient.treatment.documentation.gui.converter.SecurityAnswerConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomStatusEntryPoint implements AuthenticationEntryPoint {

    @Value("${error.title.unauthorized.access}")
    private String title;

    @Value("${error.message.unauthorized.access}")
    private String message;

    private SecurityAnswerConverter securityAnswerConverter;

    public CustomStatusEntryPoint(SecurityAnswerConverter securityAnswerConverter) {
        this.securityAnswerConverter = securityAnswerConverter;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        final HttpStatus status = HttpStatus.UNAUTHORIZED;
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(securityAnswerConverter.convertToJsonAnswer(title, message, status));

    }
}
