package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.UserMapper;
import com.patient.treatment.documentation.gui.model.security.UserPrincipal;
import com.patient.treatment.documentation.gui.session.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class LoginController {

    private SessionService sessionService;
    private UserMapper userMapper;

    public LoginController(SessionService sessionService, UserMapper userMapper) {
        this.sessionService = sessionService;
        this.userMapper = userMapper;
    }

    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@AuthenticationPrincipal Principal user) {
        sessionService.setAuthenticatedUser(((UserPrincipal) ((UsernamePasswordAuthenticationToken) user).getPrincipal()).getUser());
        return ResponseEntity.ok(userMapper.toUserDTO(((UserPrincipal) ((UsernamePasswordAuthenticationToken) user).getPrincipal()).getUser()));
    }

}
