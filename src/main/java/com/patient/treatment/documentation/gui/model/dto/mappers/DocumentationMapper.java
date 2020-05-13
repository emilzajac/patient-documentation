package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.decorators.DocumentationMapperDecorator;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.forms.DocumentationForm;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
@DecoratedWith(value = DocumentationMapperDecorator.class)
public interface DocumentationMapper {

    Documentation toDocumentationEntity(DocumentationDto documentationDto);

    Documentation toDocumentationEntity(DocumentationForm documentationForm);

    Documentation toDocumentationEntity(DocumentationProjection documentationProjection);

    DocumentationDto toDocumentationDto(Documentation documentation);

}
