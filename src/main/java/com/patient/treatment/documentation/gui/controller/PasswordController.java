package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.forms.ChangePasswordForm;
import com.patient.treatment.documentation.gui.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/password")
public class PasswordController {

    private final PasswordService passwordService;

    @GetMapping("/reset-token/{email}")
    public ResponseEntity<Void> sendResetToken(@PathVariable String email) {
        boolean passwordResetToken = passwordService.createPasswordResetToken(email);
        if (passwordResetToken) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/reset")
    public ResponseEntity<Void> sendResetToken(@RequestBody ChangePasswordForm changePasswordForm) {
        boolean passwordResetToken = passwordService.changePassword(changePasswordForm);
        if (passwordResetToken) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
