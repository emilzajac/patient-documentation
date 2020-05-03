package com.patient.treatment.documentation.gui.model.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Documentation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "interview")
    private String interview;

    @Column(name = "physical_examination")
    private String physicalExamination;

    @Column(name = "diagnosis_of_the_disease")
    private String diagnosisOfTheDisease;

    @Column(name = "recommendations")
    private String recommendations;

    @Column(name = "medicines")
    private String medicines;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "uuid")
    private String uuid;

    @OneToOne
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
