package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.dto.PatientDto;
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
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PutMapping
    public ResponseEntity save(@RequestBody PatientDto patient) {
        return ResponseEntity.ok(patientService.save(patient));
    }

    @GetMapping(value = "/{pesel}")
    public ResponseEntity find(@PathVariable String pesel) {
        return ResponseEntity.ok(patientService.findByPesel(pesel));
    }

    @GetMapping(value = "/doctor/{email}")
    public ResponseEntity findAllPatientsOfTheDoctor(@PathVariable String email) {
        return ResponseEntity.ok(patientService.findAllPatientsOfTheDoctor(email));
    }

    @GetMapping(value = "/{name}/{surname}")
    public ResponseEntity findAllPatientsByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        return ResponseEntity.ok(patientService.findAllByNameAndSurname(name, surname));
    }

}
