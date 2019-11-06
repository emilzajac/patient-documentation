package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.PatientDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.PatientMapper;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
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
    private final PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public Patient save(PatientDto patient) {
        if (patientRepository.findByPesel(patient.getPesel()) != null) {
            log.info("Patient with pesel {} already exist. Nothing will be done. ", patient.getFirstName());
            return new Patient();
        } else {
            String encryptedPesel = DigestUtils.sha256Hex(patient.getPesel());
            patient.setPesel(encryptedPesel);
            return patientRepository.save(patientMapper.toPatientEntity(patient));
        }
    }

    public PatientProjection findByPesel(String pesel) {
        return patientRepository.findByPesel(DigestUtils.sha256Hex(pesel));
    }

    public List<PatientProjection> findAllByFirstNameAndLastNameOrderByFirstName(String name, String surname) {
        return patientRepository.findAllByFirstNameAndLastNameOrderByFirstName(name, surname);
    }

    public List<PatientProjection> findAllPatientsOfTheDoctor(String doctorEmail) {
        return patientRepository.findAllByDocumentationsUserEmail(doctorEmail);
    }

}
