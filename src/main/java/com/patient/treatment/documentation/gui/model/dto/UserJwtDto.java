package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.enumy.RoleEnum;
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
public class UserJwtDto {

    private String token;

    private String tokenType;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Set<RoleEnum> userRoles;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

}
