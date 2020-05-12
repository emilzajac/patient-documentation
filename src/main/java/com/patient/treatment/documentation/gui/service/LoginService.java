package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.UserJwtDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.UserMapper;
import com.patient.treatment.documentation.gui.model.form.LoginForm;
import com.patient.treatment.documentation.gui.model.security.UserPrincipal;
import com.patient.treatment.documentation.gui.model.security.jwt.JwtUtils;
import com.patient.treatment.documentation.gui.session.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class LoginService {

    private AuthenticationManager authenticationManager;
    private SessionService sessionService;
    private UserMapper userMapper;
    private JwtUtils jwtUtils;

    public UserJwtDto getUserJwtDto(@RequestBody @Valid LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserJwtDto userJwt = createJwt(authentication);
        addUserToSession(authentication);
        return userJwt;
    }

    private UserJwtDto createJwt(Authentication authentication) {
        UserJwtDto userJwt = userMapper.toUserJwtDTO(((UserPrincipal) authentication.getPrincipal()).getUser());
        userJwt.setToken(jwtUtils.generateJwtToken(authentication));
        userJwt.setTokenType("Bearer");
        return userJwt;
    }

    private void addUserToSession(Authentication authentication) {
        sessionService.setAuthenticatedUser(((UserPrincipal) authentication.getPrincipal()).getUser());
    }

}
