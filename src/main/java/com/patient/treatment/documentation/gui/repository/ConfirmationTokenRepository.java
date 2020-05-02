package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.entites.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findByToken(String confirmationToken);

}
