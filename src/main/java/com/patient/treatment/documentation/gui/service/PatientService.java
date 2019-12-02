package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.mappers.PatientMapper;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.form.PatientForm;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
import com.patient.treatment.documentation.gui.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final EncryptDecryptService encryptDecryptService;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, EncryptDecryptService encryptDecryptService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.encryptDecryptService = encryptDecryptService;
    }

    public Patient create(PatientForm patientForm) {
        if (patientRepository.findByPesel(encryptDecryptService.encrypt(patientForm.getPesel())) != null) {
            log.info("Patient with pesel {} already exist. Nothing will be done. ", patientForm.getFirstName());
            throw new RuntimeException("Pacjent o takim peselu istnieje już w rejestrze");
        } else {
            patientForm.setPesel(encryptDecryptService.encrypt(patientForm.getPesel()));
            return patientRepository.save(patientMapper.toPatientEntity(patientForm));
        }
    }

    public PatientProjection findByPesel(String pesel) {
        return patientRepository.findByPesel(encryptDecryptService.encrypt(pesel));
    }

    public List<PatientProjection> findAllByFirstNameAndLastNameOrderByFirstName(String firstName, String lastName) {
        return patientRepository.findAllByFirstNameAndLastNameOrderByFirstName(firstName, lastName);
    }

    public List<PatientProjection> findAllPatientsOfTheDoctor(String doctorUserName) {
        List<PatientProjection> allByDoctorsUsername = patientRepository.findAllByDoctorsUsername(doctorUserName);
        allByDoctorsUsername.forEach(patientProjection -> patientProjection.setPesel(encryptDecryptService.decrypt(patientProjection.getPesel())));
        return allByDoctorsUsername;
    }

}
