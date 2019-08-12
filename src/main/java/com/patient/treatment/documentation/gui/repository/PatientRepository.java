package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.entites.Patient;
import com.patient.treatment.documentation.gui.model.projections.PatientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    PatientProjection findByPesel(String pesel);

    List<PatientProjection> findAllByNameAndSurnameOrderByName(String name, String surname);

    List<PatientProjection> findAllByDocumentationsUserEmail(String doctorEmail);

}
