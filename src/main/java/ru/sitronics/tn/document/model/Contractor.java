package ru.sitronics.tn.document.model;

public enum Contractor {
    Contractor_1("Организация_1"),
    Contractor_2("Организация_2"),
    Contractor_3("Организация_3"),
    Contractor_4("Организация_4"),
    Contractor_5("Организация_5");

    private final String translate;

    Contractor(final String translate) {
        this.translate = translate;
    }

/*    public static String getSimpleOperator(final String operator) {
        return Arrays.stream(values())
                .filter(operation -> operation.getTranslate() == operator)
                .findAny().orElse(null);
    }*/

    public String getTranslate() {
        return translate;
    }
}
