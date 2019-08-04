package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.Doctor;
import com.patient.treatment.documentation.gui.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository,
                         BCryptPasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Doctor findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    public Doctor createDoctor(Doctor doctor) {
        Doctor localDoctor = doctorRepository.findByEmail(doctor.getEmail());
        if (localDoctor != null) {
            log.info("Doctor with email {} already exist. Nothing will be done. ", doctor.getName());
        } else {
            String encryptedPassword = passwordEncoder.encode(doctor.getPassword());
            doctor.setPassword(encryptedPassword);
            localDoctor = doctorRepository.save(doctor);
        }
        return localDoctor;
    }

}
