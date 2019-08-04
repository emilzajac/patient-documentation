package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByPesel(String pesel);

    @Query("SELECT p FROM Patient p where p in (select do.patient from Documentation do where do.doctor = (select doc from Doctor doc where doc.email = :doctorEmail))")
    List<Patient> findAllWherePatientWasAssignToDoctor(@Param("doctorEmail") String doctorEmail);

}
