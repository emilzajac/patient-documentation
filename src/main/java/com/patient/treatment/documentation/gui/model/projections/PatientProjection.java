package com.patient.treatment.documentation.gui.model.projections;

import com.patient.treatment.documentation.gui.model.enums.GenderEnum;

import java.time.LocalDate;

public interface PatientProjection {

    Long getId();

    void setId(Long id);

    String getFirstName();

    void setFirstName(String name);

    String getLastName();

    void setLastName(String surname);

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

    GenderEnum getGender();

    void setGender(GenderEnum gender);

    LocalDate getDateOfBirth();

    void setDateOfBirth(LocalDate dateOfBirth);

}
