package com.patient.treatment.documentation.gui.session;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class RequestUtils {

    HttpServletRequest getHttpServletRequest() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new RuntimeException("Session can't be loaded"));
    }

}
