package ru.sitronics.tn.document.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum NciContractor {
    Contractor_1("Организация_1"),
    Contractor_2("Организация_2"),
    Contractor_3("Организация_3"),
    Contractor_4("Организация_4"),
    Contractor_5("Организация_5"),
    Contractor_6("Организация_6"),
    Contractor_7("Организация_7"),
    Contractor_8("Организация_8");

    private final String translate;

    NciContractor(final String translate) {
        this.translate = translate;
    }

    public static Map<NciContractor, String> getEnumValuesWithTranslate() {
        Map<NciContractor, String> map = new EnumMap<>(NciContractor.class);
        Arrays.asList(NciContractor.values()).forEach(value -> map.put(value, value.getTranslate()));
        return map;
    }

    public String getTranslate() {
        return translate;
    }
}
