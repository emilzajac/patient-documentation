package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = Constant.FORMAT_DATE_AND_TIME_WITH_T)
    private LocalDateTime creationDate;

    private String uuid;

    private String patientPesel;

}
