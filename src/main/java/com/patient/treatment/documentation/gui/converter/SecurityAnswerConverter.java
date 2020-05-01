package com.patient.treatment.documentation.gui.converter;

import com.google.gson.Gson;
import com.patient.treatment.documentation.gui.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SecurityAnswerConverter {

    private static final Gson CONVERTER = new Gson();

    public String convertToJsonAnswer(String title, String message, HttpStatus status) {
        return CONVERTER.toJson(ApiError.builder()
                .status(status)
                .code(status.toString())
                .title(title)
                .message(message)
                .timeStamp(LocalDateTime.now())
                .build());
    }

}
