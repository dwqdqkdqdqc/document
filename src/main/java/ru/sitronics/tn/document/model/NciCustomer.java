package ru.sitronics.tn.document.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum NciCustomer {
    CUSTOMER_1("Клиент_1"),
    CUSTOMER_2("Клиент_2"),
    CUSTOMER_3("Клиент_3"),
    CUSTOMER_4("Клиент_4"),
    CUSTOMER_5("Клиент_5"),
    CUSTOMER_6("Клиент_6"),
    CUSTOMER_7("Клиент_7"),
    ;

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
