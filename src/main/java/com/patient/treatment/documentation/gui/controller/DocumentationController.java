package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import com.patient.treatment.documentation.gui.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documentations")
public class DocumentationController {

    private final DocumentationService documentationService;

    @Autowired
    public DocumentationController(DocumentationService documentationService) {
        this.documentationService = documentationService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DocumentationForm documentationForm) {
        return ResponseEntity.ok(documentationService.create(documentationForm));
    }

    @GetMapping(value = "/patient/{pesel}")
    public ResponseEntity findByPatientPesel(@PathVariable String pesel) {
        return ResponseEntity.ok(documentationService.findByPatientPesel(pesel));
    }

}
