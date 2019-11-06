package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.DocumentationMapper;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import com.patient.treatment.documentation.gui.repository.DocumentationRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentationService {

    private final DocumentationRepository documentationRepository;
    private final DocumentationMapper documentationMapper;

    @Autowired
    public DocumentationService(DocumentationRepository documentationRepository, DocumentationMapper documentationMapper) {
        this.documentationRepository = documentationRepository;
        this.documentationMapper = documentationMapper;
    }

    public Documentation save(DocumentationDto documentation) {
        return documentationRepository.save(documentationMapper.toDocumentationEntity(documentation));
    }

    public DocumentationProjection findByPatientPesel(String pesel) {
        return documentationRepository.findAllByPatientPesel(DigestUtils.sha256Hex(pesel));
    }

}
