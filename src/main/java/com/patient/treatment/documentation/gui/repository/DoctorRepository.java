package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.dto.DoctorInterface;
import com.patient.treatment.documentation.gui.model.entites.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    DoctorInterface findByEmail(String email);

}
