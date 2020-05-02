package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.PatientDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.decorators.PatientMapperDecorator;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.form.PatientForm;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
@DecoratedWith(PatientMapperDecorator.class)
public interface PatientMapper {

    Patient toPatientEntity(PatientDto patientDto);

    Patient toPatientEntity(PatientForm patientForm);

    PatientDto toPatientDto(PatientProjection patientProjection);

    PatientDto toPatientDto(Patient patient);

}
