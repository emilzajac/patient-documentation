package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.exceptions.UnexpectedException;
import com.patient.treatment.documentation.gui.model.dto.mappers.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.ConfirmationToken;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.entites.UserRole;
import com.patient.treatment.documentation.gui.model.enumy.RoleEnum;
import com.patient.treatment.documentation.gui.model.form.UserRegisterForm;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;
import com.patient.treatment.documentation.gui.repository.ConfirmationTokenRepository;
import com.patient.treatment.documentation.gui.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private EmailSenderService emailSenderService;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       ConfirmationTokenRepository confirmationTokenRepository,
                       EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    public void createUser(UserRegisterForm userRegisterForm) {
        if (userRepository.findByEmail(userRegisterForm.getEmail()) != null) {
            log.info("User with email {} already exist. Nothing will be done. ", userRegisterForm.getEmail());
        } else {
            User savedUser = null;
            ConfirmationToken confirmationToken = null;
            try {
                savedUser = userRepository.save(createNewUser(userRegisterForm));
                confirmationToken = new ConfirmationToken(savedUser);
                confirmationTokenRepository.save(confirmationToken);
                emailSenderService.sendConfirmationEmail(confirmationToken.getUser().getEmail(), confirmationToken.getToken());
            } catch (Exception exception) {
                log.error("Error occur changes will be rollback", exception);
                if (confirmationToken != null) {
                    confirmationTokenRepository.delete(confirmationToken);
                }
                if (savedUser != null) {
                    userRepository.delete(savedUser);
                }
                throw new UnexpectedException(exception);
            }
        }
    }

    public void confirmUserAccount(@RequestParam("token") String confirmationToken) {
        Optional<ConfirmationToken> token = confirmationTokenRepository.findByToken(confirmationToken);
        if (token.isPresent()) {
            User user = userMapper.toUserEntity(userRepository.findByEmail(token.get().getUser().getEmail()));
            user.setEnabled(true);
            userRepository.save(user);
            return;
        }
        throw new UnexpectedException("Brak tokenu w systemie");
    }

    public UserProjection findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUserName(String userName) {
        User user = userMapper.toUserEntity(userRepository.findByUsername(userName));
        if (user == null) {
            log.warn("Username {} not found", userName);
            throw new UsernameNotFoundException("Username " + userName + " not found");
        }
        return user;
    }

    private User createNewUser(UserRegisterForm userRegisterForm) {
        String encryptedPassword = passwordEncoder.encode(userRegisterForm.getPassword());
        userRegisterForm.setPassword(encryptedPassword);
        User user = userMapper.toUserEntity(userRegisterForm);
        UserRole defaultUserRole = UserRole.builder()
                .user(user)
                .roleEnum(RoleEnum.DOCTOR)
                .build();
        user.setUserRoles(Collections.singleton(defaultUserRole));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return user;
    }

}
