package com.patient.treatment.documentation.gui.configuration;

import com.patient.treatment.documentation.gui.model.ControllerError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        return super.handleExceptionInternal(ex, createControllerError(), headers, status, request);
    }

    // status code = 404
    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ControllerError> resourceNotFound(Exception ex) {
        ControllerError controllerError = createControllerError(ex, HttpStatus.NOT_FOUND, "Nie ma takiego użytkownika", ex.getMessage());
        return new ResponseEntity<>(controllerError, new HttpHeaders(), controllerError.getStatus());
    }

    // Else
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ControllerError> undefinedError(Exception ex) {
        ControllerError controllerError = createControllerError(ex);
        return new ResponseEntity<>(controllerError, new HttpHeaders(), controllerError.getStatus());
    }

    private ControllerError createControllerError() {
        return ControllerError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .title("Błąd")
                .errors(Collections.singletonList("Błąd aplikacji"))
                .build();
    }

    private ControllerError createControllerError(Exception ex) {
        return ControllerError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .title("Błąd")
                .message(ex.getMessage())
                .errors(Collections.singletonList("Błąd aplikacji"))
                .build();
    }

    private ControllerError createControllerError(Exception ex, HttpStatus httpStatus, String title, String message) {
        return ControllerError.builder()
                .status(httpStatus)
                .code(httpStatus.toString())
                .title(title)
                .message(message)
                .errors(Collections.singletonList(ex.toString()))
                .build();
    }

}
