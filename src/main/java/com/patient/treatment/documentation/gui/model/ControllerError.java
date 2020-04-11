package com.patient.treatment.documentation.gui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControllerError {

    private String code;

    private HttpStatus status;

    private String title;

    private String message;

    private List<String> errors;

}
