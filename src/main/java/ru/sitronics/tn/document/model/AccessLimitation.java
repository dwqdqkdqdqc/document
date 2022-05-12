package ru.sitronics.tn.document.model;

public enum AccessLimitation { //без грифа, конфид, суп конф
    WITHOUT_A_FINGERBOARD,  //Без грифа
    COMMON, //В общем доступе
    FOR_INTERNAL_USE, //Для внутреннего использования
    COMMERCIAL_SECRET, //Коммерческий секрет
    CONFIDENTIAL_INFORMATION, //Конфиденциальная информация
    PARTICULARLY_IMPORTANT, //Особенно важно
    BANK_SECRECY, //Банковская тайна
}
