package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.Documentation;
import com.patient.treatment.documentation.gui.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documentation")
public class DocumentationController {

    private final DocumentationService documentationService;


    @Autowired
    public DocumentationController(DocumentationService documentationService) {
        this.documentationService = documentationService;
    }

    @PutMapping
    public ResponseEntity save(@RequestBody Documentation documentation) {
        return ResponseEntity.ok(documentationService.save(documentation));
    }

    @GetMapping
    public ResponseEntity find(@RequestBody String pesel){
        return ResponseEntity.ok(documentationService.findByPatientPesel(pesel));
    }
}
