package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.Patient;
import com.patient.treatment.documentation.gui.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PutMapping
    public ResponseEntity save(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.save(patient));
    }

    @GetMapping
    public ResponseEntity find(@RequestBody String pesel) {
        return ResponseEntity.ok(patientService.findByPesel(pesel));
    }

    @GetMapping(value = "/all")
    public ResponseEntity findBy(@RequestBody String email) {
        return ResponseEntity.ok(patientService.getAllWherePatientWasAssignToDoctor(email));
    }

}
