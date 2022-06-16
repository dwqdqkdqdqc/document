package ru.sitronics.tn.document.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum NciCustomer {
    Customer_1("Организация_1"),
    Customer_2("Организация_2"),
    Customer_3("Организация_3"),
    Customer_4("Организация_4"),
    Customer_5("Организация_5"),
    Customer_6("Организация_6"),
    Customer_7("Организация_7"),
    Customer_8("Организация_8");

    private final String translate;

    NciCustomer(final String translate) {
        this.translate = translate;
    }

    public static Map<NciCustomer, String> getEnumValuesWithTranslate() {
        Map<NciCustomer, String> map = new EnumMap<>(NciCustomer.class);
        Arrays.asList(NciCustomer.values()).forEach(value -> map.put(value, value.getTranslate()));
        return map;
    }

    public String getTranslate() {
        return translate;
    }
}
