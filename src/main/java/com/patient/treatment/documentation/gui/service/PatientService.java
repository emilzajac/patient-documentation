package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.Patient;
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

    public Patient findByPesel(String pesel) {
        return patientRepository.findByPesel(DigestUtils.sha256Hex(pesel));
    }

    public Patient save(Patient patient) {
        Patient localPatient = patientRepository.findByPesel(patient.getPesel());
        if (localPatient != null) {
            log.info("Patient with pesel {} already exist. Nothing will be done. ", patient.getName());
        } else {
            String encryptedPesel = DigestUtils.sha256Hex(patient.getPesel());
            patient.setPesel(encryptedPesel);
            localPatient = patientRepository.save(patient);
        }
        return localPatient;
    }

    public List<Patient> getAllWherePatientWasAssignToDoctor(String doctorEmail) {
        return patientRepository.findAllWherePatientWasAssignToDoctor(doctorEmail);
    }

}
