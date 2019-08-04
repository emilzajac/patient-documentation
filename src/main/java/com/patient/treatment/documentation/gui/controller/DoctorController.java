package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.model.Doctor;
import com.patient.treatment.documentation.gui.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PutMapping
    public ResponseEntity createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }

    //TODO For testing purpose
    @GetMapping("/z")
    public ResponseEntity saveDoctor() {
        Doctor jan = Doctor.builder()
                .name("Jan")
                .surname("Kowalski")
                .email("jancg@mail.com")
                .password("1234")
                .build();
        doctorService.createDoctor(jan);
        return ResponseEntity.ok(jan);
    }

}
