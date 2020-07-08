package com.patient.treatment.documentation.gui.service;

import com.patient.treatment.documentation.gui.exceptions.UnexpectedException;
import com.patient.treatment.documentation.gui.exceptions.UserException;
import com.patient.treatment.documentation.gui.model.dto.mappers.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.ConfirmationToken;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.forms.ChangePasswordForm;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;
import com.patient.treatment.documentation.gui.repository.ConfirmationTokenRepository;
import com.patient.treatment.documentation.gui.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PasswordService {

    private final EmailSenderService emailSenderService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public boolean changePassword(ChangePasswordForm changePasswordForm) {
        Optional<ConfirmationToken> token = confirmationTokenRepository.findByToken(changePasswordForm.getConfirmationToken());
        if (token.isPresent()) {
            User user = userMapper.toUserEntity(userRepository.findByEmail(token.get().getUser().getEmail()));
            user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
            userRepository.save(user);
            confirmationTokenRepository.delete(token.get());
            return true;
        }
        throw new UnexpectedException("Brak tokenu w systemie");
    }

    public boolean createPasswordResetToken(String email) {
        UserProjection userProjection = userRepository.findByEmail(email);
        userRepository.findById(userProjection.getId());
        User user = userRepository.findById(userProjection.getId()).orElseThrow(() -> new UserException("Nie znaleziono użytkownika", HttpStatus.NOT_FOUND));
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        try {
            emailSenderService.sendRestTokenEmail(email, confirmationToken.getToken());
        } catch (Exception exception) {
            confirmationTokenRepository.delete(confirmationToken);
            throw new UnexpectedException("Błąd Tworzenia tokenu", exception);
        }
        return true;
    }

}
