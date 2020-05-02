package com.patient.treatment.documentation.gui.model.projections;

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

    String getUuid();

    void setUuid(String uuid);

}
