package com.patient.treatment.documentation.gui.model.dto.implementation;

import com.patient.treatment.documentation.gui.model.dto.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.UserRole;
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
public class UserDTO implements UserMapper {

    private String name;

    private String surname;

    private String email;

    private String password;

    private Set<UserRole> userRoles;

}
