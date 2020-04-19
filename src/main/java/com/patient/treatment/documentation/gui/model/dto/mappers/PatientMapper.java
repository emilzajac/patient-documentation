package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.PatientDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.decorators.PatientMapperDecorator;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.form.PatientForm;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(PatientMapperDecorator.class)
public interface PatientMapper {

    PatientDto toPatientDto(PatientProjection patientProjection);

    PatientDto toPatientDto(Patient patientProjection);

    Patient toPatientEntity(PatientDto patientDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentations", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    Patient toPatientEntity(PatientForm patientForm);

}
