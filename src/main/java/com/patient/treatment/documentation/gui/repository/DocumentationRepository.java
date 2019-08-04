package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    Documentation findAllByPatientPesel(String pesel);

    Documentation findAllByDoctorEmail(String email);

}
