package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.PatientDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.PatientMapper;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.form.PatientForm;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
import com.patient.treatment.documentation.gui.repository.PatientRepository;
import com.patient.treatment.documentation.gui.session.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final SessionService sessionService;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          PatientMapper patientMapper,
                          SessionService sessionService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.sessionService = sessionService;
    }

    public Patient create(PatientForm patientForm) {
        if (patientRepository.findByPesel(patientForm.getPesel()).isPresent()) {
            log.info("Patient with pesel {} already exist. Nothing will be done. ", patientForm.getFirstName());
            throw new RuntimeException("Pacjent o takim peselu istnieje już w rejestrze");
        } else {
            Patient patient = patientMapper.toPatientEntity(patientForm);
            patient.setDoctors(Collections.singleton(sessionService.getAuthenticatedUser()));
            return patientRepository.save(patient);
        }
    }

    public PatientDto update(PatientDto patientDto) {
        Patient patient = patientMapper.toPatientEntity(patientDto);
        patient.setDoctors(patientRepository.findByPesel(patientDto.getPesel()).orElseThrow(() -> new RuntimeException("Patient not found")).getDoctors());
        patient.setDocumentations(patientRepository.findByPesel(patientDto.getPesel()).orElseThrow(() -> new RuntimeException("Patient not found")).getDocumentations());
        return patientMapper.toPatientDto(patientRepository.save(patient));
    }

    public void deleteById(long id) {
        patientRepository.deleteById(id);
    }

    public PatientDto findByPesel(String pesel) {
        return patientRepository.findByPesel(pesel).map(patientMapper::toPatientDto).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public List<PatientProjection> findAllByFirstNameAndLastNameOrderByFirstName(String firstName, String lastName) {
        return patientRepository.findAllByFirstNameAndLastNameOrderByFirstName(firstName, lastName);
    }

    public List<PatientProjection> findAllPatientsOfTheDoctor(String doctorUserName) {
        List<PatientProjection> allByDoctorsUsername = patientRepository.findAllByDoctorsUsername(doctorUserName);
        allByDoctorsUsername.forEach(patientProjection -> patientProjection.setPesel((patientProjection.getPesel())));
        return allByDoctorsUsername;
    }

}
