package com.patient.treatment.documentation.gui.model.forms;

import com.patient.treatment.documentation.gui.model.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientForm {

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
