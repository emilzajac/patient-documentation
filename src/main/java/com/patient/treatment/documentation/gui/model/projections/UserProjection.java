package com.patient.treatment.documentation.gui.model.projections;

import com.patient.treatment.documentation.gui.model.entites.UserRole;

import java.util.Set;

public interface UserProjection {

    Long getId();

    void setId(Long id);

    String getFirstName();

    void setFirstName(String name);

    String getUsername();

    void setUsername(String name);

    String getLastName();

    void setLastName(String surname);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Set<UserRole> getUserRoles();

    void setUserRoles(Set<UserRole> userRoles);

    boolean isAccountNonExpired();

    void setAccountNonExpired(boolean accountNonExpired);

    boolean isAccountNonLocked();

    void setAccountNonLocked(boolean accountNonLocked);

    boolean isCredentialsNonExpired();

    void setCredentialsNonExpired(boolean credentialsNonExpired);

    boolean isEnabled();

    void setEnabled(boolean enabled);

}
