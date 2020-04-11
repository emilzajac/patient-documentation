package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.security.UserPrincipal;
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

    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@AuthenticationPrincipal Principal user) {
        return ResponseEntity.ok(((UserPrincipal) ((UsernamePasswordAuthenticationToken) user).getPrincipal()).getUserDto());
    }

}
