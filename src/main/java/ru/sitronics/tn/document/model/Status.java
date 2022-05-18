package ru.sitronics.tn.document.model;

public enum Status {
    NO_VIEWED, //не просмотрено
    DRAFT, //черновик
    APPROVAL_IN_PROGRESS, //на согласовании
    APPROVED, //согласован
    VIEWED, //просмотрено
    ACTIVE, //действует
    PROVIDED, //предоставлен
    ACCEPTED, //принят
    WORK_IN_PROGRESS, //в работе
    DECLINED,  //отклонен
    REQUIRES_TO_SIGN, //требуется подписать
    REQUIRES_A_RESPONSE_SIGNATURE, //требуется ответная подпись
    REQUIRES_APPROVAL, //требует утверждение
    COMPLETED, //завершен
    CLARIFICATION_IS_EXPECTED, //ожидается уточнение
    REQUIRES_CANCELLATION, //требуется аннулирование
    CANCELLATION_EXPECTED, //ожидается аннулирование
    CANCELED, //аннулирован
    CONFIRMATION_OF_THE_OPERATOR_IS_EXPECTED, //ожидается подтверждение оператора
    RECEIPT_NOTICE_IS_EXPECTED, //ожидается извещение о получении
    SIGNATURE_IS_EXPECTED, //ожидается ответная подпись
    REQUIRES_CLARIFICATION, //требует уточнения
}
