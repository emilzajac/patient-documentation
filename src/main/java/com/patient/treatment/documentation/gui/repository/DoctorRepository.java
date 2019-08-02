package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor getDoctorById(Long id);

}
