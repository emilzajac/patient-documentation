package com.patient.treatment.documentation.gui.utils;

import java.util.Base64;

public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("Instantiating utility class");
    }

    public static String decode(String string) {
        byte[] decodedBytes = Base64.getDecoder().decode(string);
        return new String(decodedBytes);
    }

}
