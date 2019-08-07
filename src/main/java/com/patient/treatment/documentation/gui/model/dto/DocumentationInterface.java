package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.entites.Doctor;
import com.patient.treatment.documentation.gui.model.entites.Patient;

public interface DocumentationInterface {

    String getInterview();

    void setInterview(String interview);

    String getPhysicalExamination();

    void setPhysicalExamination(String physicalExamination);

    String getDiagnosisOfTheDisease();

    void setDiagnosisOfTheDisease(String diagnosisOfTheDisease);

    String getRecommendations();

    void setRecommendations(String recommendations);

    String getMedicines();

    void setMedicines(String medicines);

    Doctor getDoctor();

    void setDoctor(Doctor doctor);

    Patient getPatient();

    void setPatient(Patient patient);

}
