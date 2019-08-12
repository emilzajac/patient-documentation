package com.patient.treatment.documentation.gui.model.projections;

import com.patient.treatment.documentation.gui.model.entites.UserRole;

import java.util.Set;

public interface UserProjection {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getUsername();

    void setUsername(String name);

    String getSurname();

    void setSurname(String surname);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Set<UserRole> getUserRoles();

    void setUserRoles(Set<UserRole> userRoles);

}
