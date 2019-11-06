package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.service.UserService;
import com.patient.treatment.documentation.gui.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<UserDto> login(@RequestHeader("authorization") String basicAuth) {
        String username = FileUtils.decode(basicAuth.replace("Basic ", "")).split(":")[0];
        return ResponseEntity.ok(userService.findByUserName(username));
    }

}
