package com.patient.treatment.documentation.gui.model.dto;

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
public class DoctorDTO implements DoctorInterface {

    private String name;

    private String surname;

    private String email;

    private String password;

}
