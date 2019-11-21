package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.entites.UserRole;
import com.patient.treatment.documentation.gui.model.enumy.RoleEnum;
import com.patient.treatment.documentation.gui.model.form.UserRegisterForm;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;
import com.patient.treatment.documentation.gui.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

    public User createUser(UserRegisterForm userRegisterForm) {
        if (userRepository.findByEmail(userRegisterForm.getEmail()) != null) {
            log.info("User with email {} already exist. Nothing will be done. ", userRegisterForm.getFirstName());
            return new User();
        } else {
            String encryptedPassword = passwordEncoder.encode(userRegisterForm.getPassword());
            userRegisterForm.setPassword(encryptedPassword);
            User user = userMapper.toUserEntity(userRegisterForm);
            UserRole defaultUserRole = UserRole.builder()
                    .user(user)
                    .roleEnum(RoleEnum.DOCTOR)
                    .build();
            user.setUserRoles(Collections.singleton(defaultUserRole));
            return userRepository.save(user);
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
