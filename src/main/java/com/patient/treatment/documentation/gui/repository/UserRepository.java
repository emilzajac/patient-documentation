package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserProjection findByEmail(String email);

    UserProjection findByUsername(String username);

}
