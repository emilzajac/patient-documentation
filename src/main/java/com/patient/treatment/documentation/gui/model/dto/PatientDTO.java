package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.enumy.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO implements PatientInterface {

    private Long id;

    private String name;

    private String surname;

    private String pesel;

    private String city;

    private String street;

    private String houseNumber;

    private String postCode;

    private SexEnum sexEnum;

    private LocalDateTime dateOfBirth;

    private String phoneNumber;

    private List<Documentation> documentations;

    @Override
    public String toString() {
        return "";
    }


}
