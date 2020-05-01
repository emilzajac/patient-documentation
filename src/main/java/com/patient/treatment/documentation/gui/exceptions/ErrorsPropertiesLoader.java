package com.patient.treatment.documentation.gui.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

class ErrorsPropertiesLoader {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("errors", new Locale("pl"));

    private ErrorsPropertiesLoader() {
    }

    static String getMessage(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}
