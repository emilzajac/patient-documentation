package com.patient.treatment.documentation.gui.model.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patient.treatment.documentation.gui.model.enumy.GenderEnum;
import com.patient.treatment.documentation.gui.service.AttributeCipher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    @Convert(converter = AttributeCipher.class)
    private String pesel;

    private String city;

    private String street;

    private String houseNumber;

    private String postCode;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private LocalDate dateOfBirth;

    @ManyToMany
    @JoinTable(name = "patient_doctor",
            joinColumns = {@JoinColumn(name = "patient_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id")}
    )
    private Set<User> doctors = new HashSet<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Documentation> documentations;

}
