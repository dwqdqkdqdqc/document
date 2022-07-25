package ru.sitronics.tn.document.model;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(javax.persistence.AccessType.FIELD)
@Table(name = "nci_document_types")
public class NciDocumentType extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "name_rus")
    private String nameRus;

    public enum NciDocumentTypeEnum {
        DOCUMENT("Документ"),
        CONTRACT("Контракт"),
        MTR_SUPPLY_CONTRACT("Договор на поставку МТР (включая дополнительные соглашения)"),
        SPECIFICATION_TO_THE_MTR_CONTRACT("Спецификация к договору МТР"),
        INFORMATION_ABOUT_SUPPLIERS_CHAIN_OF_OWNERSHIP("Сведения о цепочке собственников Поставщика"),
        MTR_INSURANCE_POLICY("Полис страхования МТР"),
        MTR_PRODUCTION_AND_SHIPMENT_PLAN("План изготовления и отгрузки МТР"),
        PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR("Информация о ходе изготовления и подготовке к отгрузке МТР"),
        DESIGN_DOCUMENTS_FOR_THE_SUPPLIER("Конструкторская документация (для поставщика)"),
        KMD_WORKING_DOCUMENTATION("Рабочая документация по КМД"),
        ACT_OR_DOCUMENT_ON_KMD_APPROVAL("Акт/документ о согласовании КМД"),
        ACTS_OF_DETECTED_DEFECTS_OR_PRODUCT_FAILURES_WITHIN_THE_SCOPE_OF_THE_WARRANTY_CASE("Акты о выявленных дефектах либо отказах продукции в рамках гарантийного случая"),
        ACTS_OF_DEFECTS_OR_PRODUCT_FAILURES_WITHIN_THE_WARRANTY_PERIOD("Акты о выявленных дефектах либо отказах продукции в рамках гарантийного случая(более 2 лет)"),
        ACT_OF_INSPECTION_OF_PRODUCT_MANUFACTURING("Акт проверки изготовления продукции"),
        PROTOCOL_FOR_ACCEPTANCE_TESTS_OF_PRODUCTS("Протокол приемо-сдаточных испытаний продукции"),
        INFORMING_ABOUT_HAZARDOUS_SUBSTANCES("Информирование об опасных веществах"),
        NOTIFICATION_OF_CHANGE_OF_SHIPPING_DETAILS("Уведомление о смене отгрузочных реквизитов"),
        AGREEMENT_ON_THE_SUPPLY_OF_PRODUCTS_IN_ACCOUNT_OF_WHICH_ADVANCE_PAYMENT("Соглашение о поставке продукции, в счет которой уплачен аванс (пр. 6 к договору на поставку МТР)"),
        RECONCILIATION_ACT_ON_THE_PART_OF_THE_SUPPLIER("Акт сверки со стороны Поставщика"),
        RECONCILIATION_ACT_ON_THE_PART_OF_OST("Акт сверки со стороны ОСТ"),
        CARGO_CUSTOMS_DECLARATION("Грузовая таможенная декларация"),
        WAYBILL("Транспортная накладная"),
        SPECIFICATION("Спецификация"),
        QUALITY_DOCUMENTS("Документы о качестве(проставление отметки ТН)"),
        PASSPORT_OR_CERTIFICATE("Паспорт или сертификат качества"),
        RAILWAY_WAYBILL("Транспортная железнодорожная накладная (копия) ГУ-27"),
        INSTRUCTION_TO_ACCEPT_MTR_FOR_STORAGE("Поручение на прием МТР на хранение"),
        UNIT_RATE_CONTRACT("Договор по единичным расценкам"),
        RECEIPT_OF_CARGO_RECEPTION("Квитанция о приеме груза"),
        AIR_WAYBILL("Авиа-накладная"),
        RECEIPT_OF_POSTAL_ITEMS("Квитанция о приеме почтовых отправлений"),
        INFORMATION_ABOUT_THE_COST_OF_ZIP_AND_INDIVIDUAL_UNITS_MTR("Сведения о стоимости ЗИП и отдельных единиц МТР"),
        PASSPORT_OR_CERTIFICATE_OF_QUALITY_FOR_THE_SUPPLIER_FACTORY("Паспорт или сертификат качества для завода поставщика"),
        ACT_ON_THE_RESULTS_OF_THE_INSPECTION_OF_PRODUCTS("Акт о результатах проверки изделий"),
        ACT_ON_RECEIPT_OF_INVENTORY_OS_14_M_4("Акт о приеме МТР (ОС-14, М-4)"),
        ACT_ON_THE_TRANSFER_OF_INVENTORY_OS_15_M_15("Акт о передаче МТР (ОС-15, М-15)"),
        ACT_OF_DETECTION_OF_DEFECTS_IN_MATERIALS_OR_EQUIPMENT_OS_16_M_7("Акт выявления дефектов материалов/оборудования (ОС-16, М-7)"),
        NOTIFICATION_OF_ACCEPTANCE_FOR_SAFE_CUSTODY("Уведомление о приемке на ответственное хранение"),
        ACT_OF_ACCEPTANCE_OF_SERVICES_RENDERED_FOR_RESPONSIBLE_STORAGE_OF_PRODUCTS_NOT_ACCEPTED_BY_THE_BUYER("Акт сдачи-приемки оказанных услуг по ответственному хранению не принятой Покупателем Продукции"),
        NOTIFICATION_SPECIFYING_THE_PLACE_AND_TIME_OF_THE_PNR_OR_SH_MR("Уведомление с указанием места и времени проведения ПНР/ШМР"),
        ACT_ON_THE_PNR_OR_SH_MR("Акт по ПНР/ШМР"),
        DEFECTIVE_ACT("Дефектный акт (письмо-уведомление и акт в свободной форме)"),
        PRICE_LIST_CONTRACT_WITH_APPENDICES_INCLUDING_APPLICATIONS_AND_SPECIFICATIONS("Прейскурантный договор с приложениями, включая заявки и спецификации"),
        APPLICATION_TO_THE_CONTRACT_AT_UNIT_RATES("Заявка (к договору по единичным расценкам)"),
        SPECIFICATION_TO_THE_CONTRACT_FOR_UNIT_RATES("Спецификация (к договору по единичным расценкам)"),
        ACT_ON_THE_RECOGNITION_OF_PRODUCTS_AS_DEFECTIVE("Акт о признании продукции дефектной (о признании продукции не соответствующей количеству и/или комплектности и/или марке)"),
        TN_SURVEILLANCE_CONTRACT("Договор ТН-Надзор"),
        OST_APPLICATION("Заявка ОСТ (план поставок, спецификация на поднадзорную продукцию, указанная в плане поставок)"),
        REPORT_ON_SUPERVISED_PRODUCTS_THAT_HAVE_UNDERGONE_TECHNICAL_SUPERVISION_AT_MANUFACTURING_ENTERPRISES("Отчет по поднадзорной продукции, прошедшей технический надзор на предприятиях-изготовителях"),
        TECHNICAL_ACT("Технический акт"),
        ACT_OF_ACCEPTANCE_OF_SERVICES_RENDERED("Акт сдачи-приёмки оказанных услуг"),
        CONTRACT_WITH_A_LOGISTICS_OPERATOR("Договор с логистическим оператором (перевозка и экспедирование груза)"),
        POWER_OF_ATTORNEY("Доверенность"),
        TNL_ORDER_PLUS_COST_APPROVAL_PROTOCOL("Поручение ТНЛ+Протокол согласования стоимости"),
        ACT_OF_SERVICES_RENDERED("Акт оказанных услуг"),
        PAYMENT_INVOICE("Счёт на оплату"),
        SPECIAL_PERMISSION_FOR_CTG("Спецразрешение на КТГ"),
        APPLICATION_FOR_CARGO_TRANSPORTATION_GU_12_FORM("Заявка на перевозку грузов (форма ГУ-12)"),
        PROTOCOL_OF_AGREEMENT_OF_UNIT_RATES_FOR_THE_LIST_OF_SERVICES("Протокол согласования единичных расценок по перечню услуг"),
        ACT_OF_ACCEPTANCE_AND_DELIVERY_OF_SERVICES_RENDERED_BY_TNL("Акт приемки- сдачи оказанных услуг ТНЛ"),
        CONTRACT_FOR_THE_IMPLEMENTATION_OF_THE_CMR("Договор на выполнение СМР (включая дополнительные соглашения)"),
        MONTHLY_AND_DAILY_SCHEDULE_OF_WORK("Месячно-суточный график выполнения работ"),
        PROGRESS_REPORT_ON_THE_DELIVERY_OF_MATERIALS_AND_EQUIPMENT_BY_THE_CONTRACTOR("Отчет о ходе поставки материалов и оборудования Подрядчиком"),
        CONTRACTOR_S_CHAIN_OF_OWNERSHIP("Цепочка собственников Подрядчика"),
        PLAN_FOR_INVOLVING_CO_EXECUTORS_FROM_AMONG_SMALL_AND_MEDIUM_SIZED_BUSINESSES_IN_THE_EXECUTION_OF_THE_CONTRACT("План привлечения Соисполнителей из числа субъектов малого и среднего предпринимательства к исполнению контракта"),
        INFORMATION_ABOUT_THE_CONCLUSION_OF_THE_CONTRACT_WITH_THE_CO_EXECUTOR_FROM_AMONG_SMALL_AND_MEDIUM_SIZED_BUSINESSES("Сведения о заключении договора с Соисполнителем из числа субъектов малого и среднего предпринимательства"),
        INFORMATION_ON_CO_EXECUTORS("Информация по Соисполнителям"),
        BANK_GUARANTEE("Банковская гарантия"),
        CMR_INSURANCE_POLICY("Полис страхования СМР"),
        STATEMENT_OF_PROCESSING_OF_CUSTOMER_S_GOODS_MADE_ON_DELIVERY("Ведомость переработки давальческих материалов поставки заказчика"),
        REGISTER_OF_MOUNTED_EQUIPMENT_OF_THE_CUSTOMER_S_DELIVERY("Реестр смонтированного оборудования поставки Заказчика"),
        REGISTER_OF_MOUNTED_EQUIPMENT_OF_THE_CONTRACTOR_S_DELIVERY("Реестр смонтированного оборудования поставки Подрядчика"),
        STATEMENT_OF_THE_EQUIPMENT_INSTALLATION_OF_WHICH_IS_STARTED("Ведомость оборудования, монтаж которого начат"),
        STATEMENT_OF_ACCOUNTING_OF_EQUIPMENT_SUPPLIED_BY_THE_CONTRACTOR("Ведомость учета поставленных Подрядчиком оборудования"),
        ACT_OF_RECEIPT_OF_TANGIBLE_ASSETS_RECEIVED_DURING_DISASSEMBLY_AND_DISMANTLING_OF_FIXED_ASSETS_M_35("Акт об оприходовании материальных ценностей, полученных при разборке и демонтаже основных средств (М-35)"),
        INVOICE("Счёт-фактура (в т.ч. корректировочный)"),
        INVOICE_FOR_PAYMENT("Счет на оплату"),
        TORG_12_BILL_OF_LADING("ТОРГ 12, товарная накладная"),
        KS_2_AN_ACT_OF_ACCEPTANCE_OF_WORK_PERFORMED("КС-2, акт о приемке выполненных работ (в т.ч. корректировочный)"),
        KC_3_CERTIFICATE_OF_THE_COST_OF_WORK_PERFORMED_AND_COSTS("КС-3, справка о стоимости выполненных работ и затрат (в т.ч. корректировочный)"),
        KS_6_A_LOG_OF_COMPLETED_WORK("КС-6а, журнал учета выполненных работ"),
        ACT_OF_ACCEPTANCE_TRANSFER_OF_MATERIAL_ASSETS_FOR_STORAGE("Акт о приеме-передаче товарно-материальных ценностей на хранение (Типовая форма № МХ-1)"),
        ACT_ON_THE_RETURN_OF_INVENTORY_ITEMS_DEPOSITED("Акт о возврате товарно-материальных ценностей, сданных на хранение (Типовая форма № МХ-3)"),
        AGREEMENT_ON_THE_DEFINITION_OF_WORK_FOR_WHICH_AN_ADVANCE_PAYMENT_WAS_MADE("Соглашение об определении работ (поставок материалов),в счет которых уплачен аванс (часть аванса)"),
        BILL_OF_SALE_OF_MATERIALS_TO_EXTERNAL_PARTIES("Накладная на отпуск материалов на сторону (Типовая форма М-15)"),
        FORM_KC_11_ACT_OF_ACCEPTANCE_OF_COMPLETED_CONSTRUCTION("Форма КС-11, акт приемки законченного строительством Объекта"),
        FORM_KS_14_ACT_OF_ACCEPTANCE_OF_THE_COMPLETED_CONSTRUCTION_OF_THE_FACILITY_BY_THE_ACCEPTANCE_COMMISSION("Форма КС-14, акт приемки законченного строительством Объекта приемочной комиссией"),
        FORM_F_36_THE_ACT_OF_ACCEPTANCE_INTO_OPERATION_OF_THE_COMPLETED_OVERHAUL_OF_THE_OBJECT("Форма Ф-36, акт приемки в эксплуатацию законченного капитальным ремонтом объекта"),
        FORM_OS_3_ACT_OF_ACCEPTANCE_OF_REPAIRED_RECONSTRUCTED_MODERNIZED_FIXED_ASSETS("Форма ОС-3, Акт о приеме-сдаче отремонтированных, реконструированных, модернизированных объектов основных средств"),
        LIST_OF_EQUIPMENT_TRANSFERRED_TO_PROVIDE_WORK_TO_THE_CONTRACTOR("Перечень оборудования, передаваемого для обеспечения работ подрядной организации"),
        TRANSFER_AND_ACCEPTANCE_ACT_OF_THE_EQUIPMENT_TRANSFERRED_TO_SUPPORT_THE_WORK("Акт приема-передачи Оборудования, передаваемого для обеспечения работ"),
        ACT_OF_SET_OFF_OF_MUTUAL_CLAIMS_ON_THE_PART_OF_THE_COUNTERPARTY("Акт зачета взаимных требований со стороны Контрагента"),
        ACT_OF_SET_OFF_OF_RECIPROCAL_CLAIMS_ON_THE_PART_OF_OST("Акт зачета взаимных требований со стороны ОСТ"),
        WORKING_DOCUMENTATION_FOR_KMD("Рабочая документация по КМД"),
        DESIGN_DOCUMENTATION_FOR_THE_CONTRACTOR("Конструкторская документация для контрагента"),
        REMARKS_ON_THE_WORKING_DOCUMENTATION_OF_KMD("Замечания к рабочей документации по КМД"),
        ACT_OR_DOCUMENT_ON_THE_APPROVAL_OF_KMD("Акт/документ о согласовании КМД"),
        PROJECT_OF_WORK_EXECUTION("Проект производства работ (ППР)"),
        CLAIM_OR_DEMAND_FOR_LIQUIDATED_DAMAGES_INITIATOR_OST("Претензия / требование об уплате неустойки (инициатор - ОСТ)"),
        CLAIM_OR_DEMAND_FOR_LIQUIDATED_DAMAGES_INITIATOR_CONTRACTOR("Претензия / требование об уплате неустойки (инициатор - Контрагент)"),
        PAYMENT_DOCUMENT_ON_PAYMENT_OF_PENALTIES("Платежный документ об оплате неустойки"),
        CLAIMS_SETTLEMENT_DOCUMENT("Документ об урегулировании претензионных требовании"),
        GENERAL_DOCUMENT_INFORMATION_ON_FORENSIC_ACTIVITIES("Общий документ -сведения о судебно-исковой деятельности (постановления, решения, определения суда и т.д.)"),
        ACT_ON_THE_NEED_TO_PERFORM_CERTIFICATION_OF_ADDITIONAL_OR_EXCLUDED_WORKS_KOR_01("Акт о необходимости выполнения (освидетельствования) дополнительных/исключаемых работ (КОР-01)"),
        STATEMENT_OF_DEFECTS_OR_DEFECTS_DETECTED_WITHIN_THE_WARRANTY_PERIOD("Акт о выявленных дефектах/недостатках в Гарантийный срок"),
        STATEMENT_OF_RECTIFICATION_OF_DEFECTS_OR_DEFECTS_WITHIN_THE_WARRANTY_PERIOD("Акт об устранении дефектов/недостатков в Гарантийный срок"),
        SCHEDULE_FOR_MOBILIZATION_OF_HUMAN_AND_TECHNICAL_RESOURCES_AT_THE_SITE_IN_ORDER_TO_ELIMINATE_DEFECTS_OR_DEFICIENCIES("График мобилизации людских и технических ресурсов на объект в целях устранения дефектов/недостатков"),
        SCHEDULE_OF_ELIMINATION_OF_DETECTED_DEFECTS_OR_DEFECTS_DURING_THE_WARRANTY_PERIOD("График устранения выявленных дефектов/недостатков в гарантийный срок"),
        WARNING_OR_RECOMMENDATION_OF_DETECTED_VIOLATIONS_OF_THE_SK("Предупреждение/предписание о выявленных нарушениях СК"),
        NOTIFICATION_OF_RECTIFICATION_OF_IDENTIFIED_VIOLATIONS_OF_THE_SK("Уведомление об устранении выявленных нарушений СК"),
        PASSPORT_OR_CERTIFICATE_OF_QUALITY_FOR_THE_COUNTERPARTY("Паспорт или сертификат качества"),
        ACT_OF_INCOMING_INSPECTION_OF_MATERIALS_AND_EQUIPMENT("Акт входного контроля материалов и оборудования"),
        NOTICE_OF_OFFSET_OF_COLLATERAL_AMOUNT("Уведомление о зачете суммы обеспечения"),
        NOTIFICATION_OF_OFFSET_OF_AMOUNT_OF_ADVANCE_PAYMENT_ISSUED("Уведомление о зачете суммы выданного аванса"),
        NOTICE_OF_SECURITY_RETAINER_IN_LIEU_OF_CONTRACT_SECURITY("Уведомление о гарантийном удержании взамен обеспечения по контракту"),
        STATEMENT_OF_THE_COST_OF_WORK_AND_SERVICES_PERFORMED_MATERIALS_AND_EQUIPMENT_DELIVERED("Справка о стоимости выполненных работ и услуг, поставленных материалов и оборудования"),
        SPECIFICATION_FOR_SPARE_PARTS("Спецификация по запасным частям");

        private final String translate;

        NciDocumentTypeEnum(final String translate) {
            this.translate = translate;
        }

        public static Map<NciDocumentTypeEnum, String> getEnumValuesWithTranslate() {
            Map<NciDocumentTypeEnum, String> map = new EnumMap<>(NciDocumentTypeEnum.class);
            Arrays.asList(NciDocumentTypeEnum.values()).forEach(value -> map.put(value, value.getTranslate()));
            return map;
        }

        public String getTranslate() {
            return translate;
        }
    }
}
