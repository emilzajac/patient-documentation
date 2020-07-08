package com.patient.treatment.documentation.gui.model.forms;

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
public class ChangePasswordForm {

    private String email;

    private String confirmationToken;

    private String newPassword;

    private String confirmPassword;

}
