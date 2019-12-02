package com.patient.treatment.documentation.gui.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<UserRoleDto> userRoles;

}
