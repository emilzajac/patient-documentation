package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.DocumentationMapper;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import com.patient.treatment.documentation.gui.repository.DocumentationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentationService {

    private final DocumentationRepository documentationRepository;
    private final DocumentationMapper documentationMapper;

    @Autowired
    public DocumentationService(DocumentationRepository documentationRepository, DocumentationMapper documentationMapper) {
        this.documentationRepository = documentationRepository;
        this.documentationMapper = documentationMapper;
    }

    public Documentation create(DocumentationForm documentationForm) {
        Documentation documentationEntity = documentationMapper.toDocumentationEntity(documentationForm);
        return documentationRepository.save(documentationEntity);
    }

    public Documentation update(DocumentationDto documentationDto) {
        return documentationRepository.save(documentationMapper.toDocumentationEntity(documentationDto));
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
