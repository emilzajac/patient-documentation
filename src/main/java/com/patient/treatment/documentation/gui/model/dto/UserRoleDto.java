package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.enumy.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto implements Serializable {

    private User user;

    private RoleEnum roleEnum;

}
