package com.patient.treatment.documentation.gui.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:/messages_pl.properties")
public class EmailSenderService {

    @Value("${message.confirmation.token.subject}")
    private String subjectOfConfirmationTokenMessage;

    @Value("${message.confirmation.token.form}")
    private String fromOfConfirmationTokenMessage;

    @Value("${message.confirmation.token.text}")
    private String textOfConfirmationTokenMessage;

    @Value("${application.address.url}")
    private String addressUrl;

    private JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendConfirmationEmail(String emailAddress, String confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailAddress);
        mailMessage.setSubject(subjectOfConfirmationTokenMessage);
        mailMessage.setFrom(fromOfConfirmationTokenMessage);
        mailMessage.setText(textOfConfirmationTokenMessage + addressUrl + "/confirm/account?token=" + confirmationToken);
        javaMailSender.send(mailMessage);
    }

}
