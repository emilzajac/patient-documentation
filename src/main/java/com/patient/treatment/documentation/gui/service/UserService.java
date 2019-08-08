package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.info("User with email {} already exist. Nothing will be done. ", user.getName());
            return new User();
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            return userRepository.save(user);
        }
    }

    public UserMapper findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
