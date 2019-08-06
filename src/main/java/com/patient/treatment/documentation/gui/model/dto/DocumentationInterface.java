package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.entites.Doctor;
import com.patient.treatment.documentation.gui.model.entites.Patient;

public interface DocumentationInterface {

    public String getInterview();

    public void setInterview(String interview);

    public String getPhysicalExamination();

    public void setPhysicalExamination(String physicalExamination);

    public String getDiagnosisOfTheDisease();

    public void setDiagnosisOfTheDisease(String diagnosisOfTheDisease);

    public String getRecommendations();

    public void setRecommendations(String recommendations);

    public String getMedicines();

    public void setMedicines(String medicines);

    public Doctor getDoctor();

    public void setDoctor(Doctor doctor);

    public Patient getPatient();

    public void setPatient(Patient patient);

}
