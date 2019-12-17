package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.entites.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentationDto {

    private long id;

    private String interview;

    private String physicalExamination;

    private String diagnosisOfTheDisease;

    private String recommendations;

    private String medicines;

    private LocalDateTime creationDate;

    private User user;

    private Patient patient;

}
