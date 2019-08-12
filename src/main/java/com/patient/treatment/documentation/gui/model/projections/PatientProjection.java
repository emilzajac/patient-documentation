package com.patient.treatment.documentation.gui.model.projections;

import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.enumy.SexEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientProjection {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getPesel();

    void setPesel(String pesel);

    String getCity();

    void setCity(String city);

    String getStreet();

    void setStreet(String street);

    String getHouseNumber();

    void setHouseNumber(String houseNumber);

    String getPostCode();

    void setPostCode(String postCode);

    SexEnum getSexEnum();

    void setSexEnum(SexEnum sexEnum);

    LocalDateTime getDateOfBirth();

    void setDateOfBirth(LocalDateTime dateOfBirth);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    List<Documentation> getDocumentations();

    void setDocumentations(List<Documentation> documentations);

}
