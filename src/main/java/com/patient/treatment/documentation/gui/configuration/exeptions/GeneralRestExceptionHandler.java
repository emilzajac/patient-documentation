package com.patient.treatment.documentation.gui.configuration.exeptions;

import com.patient.treatment.documentation.gui.model.ApiError;
import com.patient.treatment.documentation.gui.utils.ExceptionsUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralRestExceptionHandler extends ResponseEntityExceptionHandler {

    private ExceptionsUtils exceptionsUtils;

    public GeneralRestExceptionHandler(ExceptionsUtils exceptionsUtils) {
        this.exceptionsUtils = exceptionsUtils;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        return super.handleExceptionInternal(exception, exceptionsUtils.createApiError(), headers, status, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiError> createApiUndefinedError(Exception exception) {
        ApiError controllerError = exceptionsUtils.createApiError(exception);
        return new ResponseEntity<>(controllerError, new HttpHeaders(), controllerError.getStatus());
    }

}
