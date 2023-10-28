package com.SoundBox.core.enums;

import lombok.Getter;

@Getter
public enum GenderEnum {

    OUTRO("O"),
    MASCULINO("M"),
    FEMININO("F");

    private String label;

    private GenderEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}