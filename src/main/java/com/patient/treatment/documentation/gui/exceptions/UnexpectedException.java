package com.patient.treatment.documentation.gui.exceptions;

public class UnexpectedException extends RuntimeException {

    private static final String MESSAGE = ErrorsPropertiesLoader.getMessage("error.message.occur.unexpected.error");

    public UnexpectedException() {
        super(MESSAGE);
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
