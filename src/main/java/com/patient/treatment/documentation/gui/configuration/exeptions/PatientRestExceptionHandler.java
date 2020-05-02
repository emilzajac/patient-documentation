package com.patient.treatment.documentation.gui.configuration.exeptions;

import com.patient.treatment.documentation.gui.exceptions.PatientException;
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
public class PatientRestExceptionHandler {

    private ExceptionsUtils exceptionsUtils;

    public PatientRestExceptionHandler(ExceptionsUtils exceptionsUtils) {
        this.exceptionsUtils = exceptionsUtils;
    }

    @ExceptionHandler({PatientException.class})
    public ResponseEntity<ApiError> createApiResponse(PatientException exception) {
        ApiError apiError = exceptionsUtils.createApiError(exception, exception.getStatusCode(), "Błąd Pacjenta", exception.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
