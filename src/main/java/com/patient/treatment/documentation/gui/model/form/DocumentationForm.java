package com.patient.treatment.documentation.gui.model.form;

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
public class DocumentationForm {

    private String interview;

    private String physicalExamination;

    private String diagnosisOfTheDisease;

    private String recommendations;

    private String medicines;

    //    @JsonFormat(pattern = "dd.MM.yyyy'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    private User user;

    private Patient patient;

}
