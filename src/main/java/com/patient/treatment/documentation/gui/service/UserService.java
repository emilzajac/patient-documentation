package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;
import com.patient.treatment.documentation.gui.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public User createUser(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.info("User with email {} already exist. Nothing will be done. ", user.getName());
            return new User();
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            return userRepository.save(userMapper.toUserEntity(user));
        }
    }

    public UserProjection findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto findByUserName(String userName) {
        UserDto user = userMapper.toUserDTO(userRepository.findByUsername(userName));
        if (user == null) {
            log.warn("Username {} not found", userName);
            throw new UsernameNotFoundException("Username " + userName + " not found");
        }
        return user;
    }

}
