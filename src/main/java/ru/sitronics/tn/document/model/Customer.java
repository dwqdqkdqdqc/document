package ru.sitronics.tn.document.model;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import ru.sitronics.tn.rsql.RsqlSearchOperation;

import java.util.Arrays;

public enum Customer {
    ORGANIZATION_1("Организация_1"),
    ORGANIZATION_2("Организация_2"),
    ORGANIZATION_3("Организация_3");

    private final String translate;

    Customer(final String translate) {
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
