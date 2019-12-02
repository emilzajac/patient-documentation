package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.DocumentationDto;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    @Mapping(target = "id", ignore = true)
    Documentation toDocumentationEntity(DocumentationDto documentationDto);

    @Mapping(target = "id", ignore = true)
    Documentation toDocumentationEntity(DocumentationForm documentationForm);

}
