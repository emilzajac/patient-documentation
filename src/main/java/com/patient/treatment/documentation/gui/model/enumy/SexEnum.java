package com.patient.treatment.documentation.gui.model.enumy;

import lombok.Getter;

@Getter
public enum SexEnum {
    FIMALE("Kobieta"),
    MALE("Mężczyzna");

    private final String kod;

    SexEnum(String kod) {
        this.kod = kod;
    }
}
