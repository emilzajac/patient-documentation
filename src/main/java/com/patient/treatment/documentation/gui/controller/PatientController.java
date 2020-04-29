package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.dto.PatientDto;
import com.patient.treatment.documentation.gui.model.form.PatientForm;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
import com.patient.treatment.documentation.gui.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PatientForm patientForm) {
        patientService.create(patientForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{pesel}")
    public ResponseEntity<PatientDto> find(@PathVariable String pesel) {
        return ResponseEntity.ok(patientService.findByPesel(pesel));
    }

    @PutMapping
    public ResponseEntity<PatientDto> update(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.update(patientDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> find(@PathVariable long id) {
        patientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/doctor/{username}")
    public ResponseEntity<List<PatientProjection>> findAllPatientsOfTheDoctor(@PathVariable String username) {
        return ResponseEntity.ok(patientService.findAllPatientsOfTheDoctor(username));
    }

    @GetMapping(value = "/{firstName}/{lastName}")
    public ResponseEntity findAllPatientsByNameAndSurname(@PathVariable String firstName, @PathVariable String lastName) {
        return ResponseEntity.ok(patientService.findAllByFirstNameAndLastNameOrderByFirstName(firstName, lastName));
    }

}
