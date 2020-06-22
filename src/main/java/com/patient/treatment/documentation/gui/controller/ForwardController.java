package com.patient.treatment.documentation.gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This Controller solve problem with reload current page, redirecting on single page in angular2
 */

@Controller
public class ForwardController {

    @GetMapping(value = "/**/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}
