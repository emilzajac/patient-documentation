package com.patient.treatment.documentation.gui.model;

import com.patient.treatment.documentation.gui.model.enumy.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String pesel;

    private String city;

    private String street;

    private String houseNumber;

    private String postCode;

    private SexEnum sexEnum;

    private LocalDateTime dateOfBirth;

    private String phoneNumber;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Documentation> documentations;

}
