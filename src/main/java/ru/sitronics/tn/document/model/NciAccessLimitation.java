package ru.sitronics.tn.document.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum NciAccessLimitation {
    WITHOUT_A_FINGERBOARD("Без грифа"),
    CONFIDENTIALLY("Конфиденциально"),
    SUPER_CONFIDENTIAL("супер конфиденциально");

    private final String translate;

    NciAccessLimitation(final String translate) {
        this.translate = translate;
    }

    public static Map<NciAccessLimitation, String> getEnumValuesWithTranslate() {
        Map<NciAccessLimitation, String> map = new EnumMap<>(NciAccessLimitation.class);
        Arrays.asList(NciAccessLimitation.values()).forEach(value -> map.put(value, value.getTranslate()));
        return map;
    }

    public String getTranslate() {
        return translate;
    }
}
