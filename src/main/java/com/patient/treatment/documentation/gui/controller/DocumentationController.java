package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documentation")
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

    @GetMapping(value = "/patient/{pesel}")
    public ResponseEntity findByPatientPesel(@PathVariable String pesel) {
        return ResponseEntity.ok(documentationService.findByPatientPesel(pesel));
    }

}
