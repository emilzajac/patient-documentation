package com.patient.treatment.documentation.gui.model.dto.mappers.decorators;

import com.patient.treatment.documentation.gui.model.dto.mappers.PatientMapper;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.form.PatientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;

public abstract class PatientMapperDecorator implements PatientMapper {

    @Autowired
    @Qualifier("delegate")
    private PatientMapper delegate;

    @Override
    public Patient toPatientEntity(PatientForm patientForm) {
        Patient patient = delegate.toPatientEntity(patientForm);
        patient.setDoctors(Collections.singleton(patientForm.getDoctor()));
        return patient;
    }

}
