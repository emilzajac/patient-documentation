package com.patient.treatment.documentation.gui.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorsPropertiesLoader {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("errors", new Locale("pl"));

    private ErrorsPropertiesLoader() {
    }

    public static String getMessage(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}
