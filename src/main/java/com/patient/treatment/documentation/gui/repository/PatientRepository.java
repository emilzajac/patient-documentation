package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.dto.PatientMapper;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    PatientMapper findByPesel(String pesel);

    List<PatientMapper> findAllByNameAndSurnameOrderByName(String name, String surname);

    List<PatientMapper> findAllByDocumentationsUserEmail(String doctorEmail);

}
