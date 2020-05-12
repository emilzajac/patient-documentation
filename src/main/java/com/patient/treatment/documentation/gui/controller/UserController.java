package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.form.UserRegisterForm;
import com.patient.treatment.documentation.gui.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterForm userRegisterForm) {
        userService.createUser(userRegisterForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity find(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

}
