package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    Documentation toDocumentationEntity(DocumentationDto documentationDto);

    @Mapping(target = "id", ignore = true)
    Documentation toDocumentationEntity(DocumentationForm documentationForm);

    Documentation toDocumentationDto(DocumentationProjection documentationProjection);

}
