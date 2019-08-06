package com.patient.treatment.documentation.gui.model.dto;

import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.enumy.SexEnum;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientInterface {

    @Value("#{target.id}")
    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getSurname();

    public void setSurname(String surname);

    public String getPesel();

    public void setPesel(String pesel);

    public String getCity();

    public void setCity(String city);

    public String getStreet();

    public void setStreet(String street);

    public String getHouseNumber();

    public void setHouseNumber(String houseNumber);

    public String getPostCode();

    public void setPostCode(String postCode);

    public SexEnum getSexEnum();

    public void setSexEnum(SexEnum sexEnum);

    public LocalDateTime getDateOfBirth();

    public void setDateOfBirth(LocalDateTime dateOfBirth);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public List<Documentation> getDocumentations();

    public void setDocumentations(List<Documentation> documentations);

}
