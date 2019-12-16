package com.patient.treatment.documentation.gui.model.projections;

import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.entites.User;

import java.time.LocalDateTime;

public interface DocumentationProjection {

    Long getId();

    void setId(Long id);

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

    LocalDateTime getCreationDate();

    void setCreationDate(LocalDateTime creationDate);

    User getUser();

    void setUser(User user);

    Patient getPatient();

    void setPatient(Patient patient);

}
