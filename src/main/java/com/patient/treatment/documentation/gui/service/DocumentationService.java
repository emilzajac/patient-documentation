package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.exceptions.DocumentationException;
import com.patient.treatment.documentation.gui.exceptions.PatientException;
import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.DocumentationMapper;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import com.patient.treatment.documentation.gui.repository.DocumentationRepository;
import com.patient.treatment.documentation.gui.repository.PatientRepository;
import com.patient.treatment.documentation.gui.session.SessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentationService {

    private final DocumentationRepository documentationRepository;
    private final DocumentationMapper documentationMapper;
    private final SessionService sessionService;
    private final PatientRepository patientRepository;

    @Autowired
    public DocumentationService(DocumentationRepository documentationRepository,
                                DocumentationMapper documentationMapper,
                                SessionService sessionService,
                                PatientRepository patientRepository) {
        this.documentationRepository = documentationRepository;
        this.documentationMapper = documentationMapper;
        this.sessionService = sessionService;
        this.patientRepository = patientRepository;
    }

    public Documentation create(DocumentationForm documentationForm) {
        Documentation documentationEntity = documentationMapper.toDocumentationEntity(documentationForm);
        documentationEntity.setDoctor(sessionService.getAuthenticatedUser());
        documentationEntity.setPatient(patientRepository.findByPesel(documentationForm.getPatientPesel())
                .orElseThrow(() -> new PatientException("Patient with this pesel not exists", HttpStatus.NOT_FOUND)));
        documentationEntity.setUuid(UUID.randomUUID().toString());
        return documentationRepository.save(documentationEntity);
    }

    public DocumentationDto update(DocumentationDto documentationDto) {
        Documentation documentation = documentationMapper.toDocumentationEntity(documentationDto);
        documentation.setPatient(documentationRepository.findById(documentationDto.getId())
                .orElseThrow(() -> new DocumentationException("Documentation not found: id=" + documentationDto.getId(), HttpStatus.NOT_FOUND)).getPatient());
        documentation.setDoctor(documentationRepository.findById(documentationDto.getId())
                .orElseThrow(() -> new DocumentationException("Documentation not found: id=" + documentationDto.getId(), HttpStatus.NOT_FOUND)).getDoctor());
        return documentationMapper.toDocumentationDto(documentationRepository.save(documentation));
    }

    public void deleteById(long id) {
        documentationRepository.deleteById(id);
    }

    public List<DocumentationProjection> findByPatient(long id, String pesel) {
        return Optional.ofNullable(pesel)
                .map(peselNumber -> documentationRepository.findAllByPatient_IdOrPatient_PeselOrderByCreationDateDesc(id, peselNumber))
                .orElseGet(() -> documentationRepository.findAllByPatient_IdOrPatient_PeselOrderByCreationDateDesc(id, StringUtils.EMPTY));
    }

    public List<DocumentationProjection> findByPatientByDoctorUsername(String doctorUsername) {
        return documentationRepository.findAllByPatient_Doctors_usernameOrderByCreationDateDesc(doctorUsername);
    }
}
