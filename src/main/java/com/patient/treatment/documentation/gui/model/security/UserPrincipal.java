package com.patient.treatment.documentation.gui.model.security;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private UserDto userDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userDto.getUserRoles().stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleEnum().name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userDto.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userDto.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.userDto.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.userDto.isEnabled();
    }

}
