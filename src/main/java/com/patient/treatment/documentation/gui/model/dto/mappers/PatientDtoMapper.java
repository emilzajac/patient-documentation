package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.PatientDto;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;

public class PatientDtoMapper {

    public static PatientDto patientProjectonToPatientDto(PatientProjection patientProjection) {
        return PatientDto.builder()
                .city(patientProjection.getCity())
                .dateOfBirth(patientProjection.getDateOfBirth())
                .documentations(patientProjection.getDocumentations())
                .houseNumber(patientProjection.getHouseNumber())
                .name(patientProjection.getName())
                .pesel(patientProjection.getPesel())
                .phoneNumber(patientProjection.getPhoneNumber())
                .postCode(patientProjection.getPostCode())
                .sexEnum(patientProjection.getSexEnum())
                .street(patientProjection.getStreet())
                .surname(patientProjection.getSurname())
                .build();
    }

    public static Patient patientDtoToPatientEntity(PatientDto patientDto) {
        return Patient.builder()
                .city(patientDto.getCity())
                .dateOfBirth(patientDto.getDateOfBirth())
                .documentations(patientDto.getDocumentations())
                .houseNumber(patientDto.getHouseNumber())
                .name(patientDto.getName())
                .pesel(patientDto.getPesel())
                .phoneNumber(patientDto.getPhoneNumber())
                .postCode(patientDto.getPostCode())
                .sexEnum(patientDto.getSexEnum())
                .street(patientDto.getStreet())
                .surname(patientDto.getSurname())
                .build();
    }

}
