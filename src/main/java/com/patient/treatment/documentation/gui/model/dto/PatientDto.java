package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String pesel;

    private String city;

    private String street;

    private String houseNumber;

    private String postCode;

    private GenderEnum gender;

    private LocalDate dateOfBirth;

}
