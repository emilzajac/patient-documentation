package com.patient.treatment.documentation.gui.model.form;

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
public class UserRegisterForm {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
