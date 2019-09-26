package com.patient.treatment.documentation.gui.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicAuthController {

    @GetMapping(path = "/basicauth")
    public ResponseEntity basicAuth() {
        return ResponseEntity.ok().build();
    }

}
