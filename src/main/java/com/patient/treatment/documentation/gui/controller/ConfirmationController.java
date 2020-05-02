package com.patient.treatment.documentation.gui.controller;

import com.patient.treatment.documentation.gui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("confirm")
@Slf4j
public class ConfirmationController {

    private final UserService userService;

    @Autowired
    public ConfirmationController(UserService userService) {
        this.userService = userService;
    }

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

