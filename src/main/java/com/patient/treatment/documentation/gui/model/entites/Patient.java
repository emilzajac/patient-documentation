package com.patient.treatment.documentation.gui.model.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patient.treatment.documentation.gui.model.enumy.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    @Enumerated(EnumType.STRING)
    private SexEnum sexEnum;

    private LocalDateTime dateOfBirth;

    private String phoneNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Documentation> documentations;

}
