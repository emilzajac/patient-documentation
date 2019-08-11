package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.entites.UserRole;

import java.util.Set;

public interface UserMapper {

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Set<UserRole> getUserRoles();

    void setUserRoles(Set<UserRole> userRoles);

}
