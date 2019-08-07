package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.dto.PatientInterface;
import com.patient.treatment.documentation.gui.model.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    PatientInterface findByPesel(String pesel);

    List<PatientInterface> findAllByNameAndSurnameOrderByName(String name, String surname);

    List<PatientInterface> findAllByDocumentationsDoctorEmail(String doctorEmail);
}
