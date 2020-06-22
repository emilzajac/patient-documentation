package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.dto.UserJwtDto;
import com.patient.treatment.documentation.gui.model.forms.LoginForm;
import com.patient.treatment.documentation.gui.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<UserJwtDto> authenticateUser(@RequestBody LoginForm loginForm) {
        UserJwtDto userJwt = loginService.getUserJwtDto(loginForm);
        return ResponseEntity.ok(userJwt);
    }

}

