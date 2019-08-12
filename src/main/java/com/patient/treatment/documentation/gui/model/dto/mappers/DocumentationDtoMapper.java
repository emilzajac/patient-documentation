package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;

public class DocumentationDtoMapper {

    public static DocumentationDto documentationProjectonToDocumentationDto(DocumentationProjection documentationProjection) {
        return DocumentationDto.builder()
                .diagnosisOfTheDisease(documentationProjection.getDiagnosisOfTheDisease())
                .interview(documentationProjection.getInterview())
                .medicines(documentationProjection.getMedicines())
                .patient(documentationProjection.getPatient())
                .physicalExamination(documentationProjection.getPhysicalExamination())
                .recommendations(documentationProjection.getRecommendations())
                .user(documentationProjection.getUser())
                .build();
    }

    public static Documentation documentationDtoToDocumentationEntity(DocumentationDto documentationDto) {
        return Documentation.builder()
                .diagnosisOfTheDisease(documentationDto.getDiagnosisOfTheDisease())
                .interview(documentationDto.getInterview())
                .medicines(documentationDto.getMedicines())
                .patient(documentationDto.getPatient())
                .physicalExamination(documentationDto.getPhysicalExamination())
                .recommendations(documentationDto.getRecommendations())
                .user(documentationDto.getUser())
                .build();
    }

}
