package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.decorators.DocumentationMapperDecorator;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
@DecoratedWith(value = DocumentationMapperDecorator.class)
public interface DocumentationMapper {

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    Documentation toDocumentationEntity(DocumentationDto documentationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    Documentation toDocumentationEntity(DocumentationForm documentationForm);

    Documentation toDocumentationDto(DocumentationProjection documentationProjection);

    DocumentationDto toDocumentationDto(Documentation documentation);

}
