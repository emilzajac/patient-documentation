package com.patient.treatment.documentation.gui.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PatientException extends RuntimeException {

    private static final String MESSAGE = ErrorsPropertiesLoader.getMessage("error.message.occur.unexpected.error");

    private final HttpStatus statusCode;

    public PatientException(HttpStatus statusCode) {
        super(MESSAGE);
        this.statusCode = statusCode;
    }

    public PatientException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public PatientException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public PatientException(Throwable cause, HttpStatus statusCode) {
        super(MESSAGE, cause);
        this.statusCode = statusCode;
    }

}
