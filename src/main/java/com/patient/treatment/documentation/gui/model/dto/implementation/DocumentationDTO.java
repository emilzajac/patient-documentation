package com.patient.treatment.documentation.gui.model.dto.implementation;

import com.patient.treatment.documentation.gui.model.dto.DocumentationMapper;
import com.patient.treatment.documentation.gui.model.entites.Doctor;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentationDTO implements DocumentationMapper {

    private String interview;

    private String physicalExamination;

    private String diagnosisOfTheDisease;

    private String recommendations;

    private String medicines;

    private Doctor doctor;

    private Patient patient;

}
