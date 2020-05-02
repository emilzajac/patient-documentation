package com.patient.treatment.documentation.gui.model.dto.mappers.decorators;

import com.patient.treatment.documentation.gui.model.dto.mappers.DocumentationMapper;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.form.DocumentationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class DocumentationMapperDecorator implements DocumentationMapper {

    @Autowired
    @Qualifier("delegate")
    private DocumentationMapper delegate;

    @Override
    public Documentation toDocumentationEntity(DocumentationForm documentationForm) {
        Documentation documentation = delegate.toDocumentationEntity(documentationForm);
        documentation.setCreationDate(documentationForm.getCreationDate());
        return documentation;
    }

}
