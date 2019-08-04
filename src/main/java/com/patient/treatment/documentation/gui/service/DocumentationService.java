package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.Documentation;
import com.patient.treatment.documentation.gui.repository.DocumentationRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentationService {

    private final DocumentationRepository documentationRepository;

    @Autowired
    public DocumentationService(DocumentationRepository documentationRepository) {
        this.documentationRepository = documentationRepository;
    }

    public Documentation save(Documentation documentation) {
        return documentationRepository.save(documentation);
    }

    public Documentation findByPatientPesel(String pesel) {
        return documentationRepository.findAllByPatientPesel(DigestUtils.sha256Hex(pesel));
    }

}
