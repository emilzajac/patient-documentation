package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("confirm")
public class ConfirmationController {

    private final UserService userService;

    @GetMapping("/account")
    public void confirmUserAccount(@RequestParam("token") String confirmationToken, HttpServletResponse response) {
        userService.confirmUserAccount(confirmationToken);
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/login");
        } catch (IOException exception) {
            log.error("Error podczas przekierowania", exception);
        }

    }

}

