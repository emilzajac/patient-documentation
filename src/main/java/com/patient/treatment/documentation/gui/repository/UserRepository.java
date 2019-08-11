package com.patient.treatment.documentation.gui.repository;

import com.patient.treatment.documentation.gui.model.dto.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserMapper findByEmail(String email);

    User findByUsername(String username);

}
