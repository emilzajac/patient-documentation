package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.PatientInterface;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(Patient patient) {
        if (patientRepository.findByPesel(patient.getPesel()) != null) {
            log.info("Patient with pesel {} already exist. Nothing will be done. ", patient.getName());
            return new Patient();
        } else {
            String encryptedPesel = DigestUtils.sha256Hex(patient.getPesel());
            patient.setPesel(encryptedPesel);
            return patientRepository.save(patient);
        }
    }

    public PatientInterface findByPesel(String pesel) {
        return patientRepository.findByPesel(DigestUtils.sha256Hex(pesel));
    }

    public List<PatientInterface> findAllByNameAndSurname(String name, String surname) {
        return patientRepository.findAllByNameAndSurnameOrderByName(name, surname);
    }

    public List<PatientInterface> findAllPatientsOfTheDoctor(String doctorEmail) {
        return patientRepository.findAllByDocumentationsDoctorEmail(doctorEmail);
    }

}
