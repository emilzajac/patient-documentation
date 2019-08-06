package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.dto.DocumentationInterface;
import com.patient.treatment.documentation.gui.model.entites.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    DocumentationInterface findAllByPatientPesel(String pesel);

    DocumentationInterface findAllByDoctorEmail(String email);

}
