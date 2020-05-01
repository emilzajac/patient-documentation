package com.patient.treatment.documentation.gui.configuration.exeptions;

import com.patient.treatment.documentation.gui.exceptions.DocumentationException;
import com.patient.treatment.documentation.gui.model.ApiError;
import com.patient.treatment.documentation.gui.utils.ExceptionsUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DocumentationRestExceptionHandler {

    private ExceptionsUtils exceptionsUtils;

    public DocumentationRestExceptionHandler(ExceptionsUtils exceptionsUtils) {
        this.exceptionsUtils = exceptionsUtils;
    }

    @ExceptionHandler({DocumentationException.class})
    public ResponseEntity<ApiError> createApiResponse(DocumentationException exception) {
        ApiError apiError = exceptionsUtils.createApiError(exception, exception.getStatusCode(), "Błąd Dokumentacji", exception.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
