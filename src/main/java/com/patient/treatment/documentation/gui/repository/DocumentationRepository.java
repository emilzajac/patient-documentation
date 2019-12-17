package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.entites.Documentation;
import com.patient.treatment.documentation.gui.model.projections.DocumentationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    List<DocumentationProjection> findAllByPatient_IdOrPatient_PeselOrderByCreationDateDesc(long id, String pesel);

}
