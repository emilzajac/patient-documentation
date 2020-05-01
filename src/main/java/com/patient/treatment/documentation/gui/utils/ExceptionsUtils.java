package com.patient.treatment.documentation.gui.utils;

import com.patient.treatment.documentation.gui.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class ExceptionsUtils {

    public ApiError createApiError() {
        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .title("Błąd")
                .message("Ogólny błąd aplikacji")
                .errors(Collections.singletonList("Błąd aplikacji"))
                .build();
    }

    public ApiError createApiError(Exception exception) {
        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .title("Błąd")
                .message(exception.getMessage())
                .errors(Collections.singletonList("Błąd aplikacji"))
                .build();
    }

    public ApiError createApiError(Exception exception, HttpStatus httpStatus, String title, String message) {
        return ApiError.builder()
                .status(httpStatus)
                .timeStamp(LocalDateTime.now())
                .code(httpStatus.toString())
                .title(title)
                .message(message)
                .errors(Collections.singletonList(exception.getMessage()))
                .build();
    }

}
