package com.patient.treatment.documentation.gui.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This Controller solve problem with reload current page, redirecting on single page in angular2
 */

@Controller
public class ForwardController implements ErrorController {

    @GetMapping(value = "/error")
    public String error() {
        return "forward:/index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
