package ru.sitronics.tn.document.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum NciStatus {
    /*    @Column(name = "internal_id")
    private Integer internalId;
    @Column(name = "status_document")
    private String statusDocument;
    @Column(name = "status_document_rus")
    private String statusDocumentRus;*/
    NO_VIEWED("не просмотрено"), //
    DRAFT("черновик"), //
    APPROVAL_IN_PROGRESS("на согласовании"), //
    APPROVED("согласован"), //
    VIEWED("просмотрено"), //
    ACTIVE("действует"), //
    PROVIDED("предоставлен"), //
    ACCEPTED("принят"), //
    WORK_IN_PROGRESS("в работе"), //
    DECLINED("отклонен"),  //
    REQUIRES_TO_SIGN("требуется подписать"), //
    REQUIRES_A_RESPONSE_SIGNATURE("требуется ответная подпись"), //
    REQUIRES_APPROVAL("требует утверждение"), //
    COMPLETED("завершен"), //
    CLARIFICATION_IS_EXPECTED("ожидается уточнение"), //
    REQUIRES_CANCELLATION("требуется аннулирование"), //
    CANCELLATION_EXPECTED("ожидается аннулирование"), //
    CANCELED("аннулирован"), //
    CONFIRMATION_OF_THE_OPERATOR_IS_EXPECTED("ожидается подтверждение оператора"), //
    RECEIPT_NOTICE_IS_EXPECTED("ожидается извещение о получении"), //
    SIGNATURE_IS_EXPECTED("ожидается ответная подпись"), //
    REQUIRES_CLARIFICATION("требует уточнения"), //
    ARCHIVAL("архивный"), //
    FILLED("заполнен"), //
    FORMED("сформирован"),
    PROJECT_CREATION("Создание проекта"),
    ASSIGNMENT_OF_RESPONSIBLE("Назначение ответственных лиц"),
    AWAITING_FOR_SIGNING("Ожидание подписания"),
    NOT_ACTIVE("Неактивный"),
    APPROVAL_IN_PROGRESS_IN_SED("На согласовании в СЭД"),
    REMARKS_PREPARED("Сформированы замечания"),
    TO_BE_SIGNED_BY_UKEP(" на подписании УКЭП"),
    TO_BE_FAMILIARIZED("На ознакомлении"),
    IN_ARCHIVED("В архиве");

    private final String translate;

    NciStatus(final String translate) {
        this.translate = translate;
    }

    public static Map<NciStatus, String> getEnumValuesWithTranslate() {
        Map<NciStatus, String> map = new EnumMap<>(NciStatus.class);
        Arrays.asList(NciStatus.values()).forEach(value -> map.put(value, value.getTranslate()));
        return map;
    }

    public String getTranslate() {
        return translate;
    }
}
