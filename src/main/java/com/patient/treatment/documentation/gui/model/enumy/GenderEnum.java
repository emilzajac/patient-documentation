package com.patient.treatment.documentation.gui.model.enumy;

import lombok.Getter;

@Getter
public enum GenderEnum {
    FEMALE("Kobieta"),
    MALE("Mężczyzna");

    private final String kod;

    GenderEnum(String kod) {
        this.kod = kod;
    }
}
