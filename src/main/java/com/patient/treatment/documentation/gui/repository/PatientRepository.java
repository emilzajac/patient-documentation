package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPesel(String pesel);

    List<PatientProjection> findAllByFirstNameAndLastNameOrderByFirstName(String firstName, String lastName);

    List<PatientProjection> findAllByDoctorsUsername(String doctorUserName);

}
