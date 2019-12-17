package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import com.patient.treatment.documentation.gui.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/documentations")
public class DocumentationController {

    private final DocumentationService documentationService;

    @Autowired
    public DocumentationController(DocumentationService documentationService) {
        this.documentationService = documentationService;
    }

    @PostMapping
    public ResponseEntity<Documentation> create(@RequestBody DocumentationForm documentationForm) {
        return ResponseEntity.ok(documentationService.create(documentationForm));
    }

    @PutMapping
    public ResponseEntity<Documentation> update(@RequestBody DocumentationDto documentationDto) {
        return ResponseEntity.ok(documentationService.update(documentationDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> find(@PathVariable long id) {
        documentationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/patient")
    public ResponseEntity<List<DocumentationProjection>> findByPatient(@RequestParam(required = false) long id,
                                                                       @RequestParam(required = false) String pesel) {
        return ResponseEntity.ok(documentationService.findByPatient(id, pesel));
    }

}
