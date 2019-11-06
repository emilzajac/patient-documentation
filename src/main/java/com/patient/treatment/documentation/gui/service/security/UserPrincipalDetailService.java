package com.patient.treatment.documentation.gui.service.security;

import com.patient.treatment.documentation.gui.model.security.UserPrincipal;
import com.patient.treatment.documentation.gui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserPrincipalDetailService implements UserDetailsService {

    private final UserService userService;

    public UserPrincipalDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserPrincipal(userService.findByUserName(username));
    }

}
