/*
DELETE FROM documents;
DELETE FROM nci_users;
DELETE FROM documents_curators;
DELETE FROM type_links;
DELETE FROM documents_link_documents;
DELETE FROM documents_history_bpm;
DELETE FROM nci_objects_kis_up;
DELETE FROM documents_objects;
DELETE FROM nci_objects;
DELETE FROM documents_construction_objects;
DELETE FROM nci_osts;
DELETE FROM attachments;
DELETE FROM documents_attachments;
DELETE FROM nci_customers;
DELETE FROM nci_document_types;
DELETE FROM nci_mtr_groups;
DELETE FROM nci_mtrs;
DELETE FROM nci_phases;
DELETE FROM nci_class_contracts;
DELETE FROM nci_standard_forms;
DELETE FROM nci_termination_codes;
DELETE FROM nci_countries;
DELETE FROM nci_types_of_transport;
DELETE FROM nci_consignees;
DELETE FROM specification_table_entities;
DELETE FROM nci_units_measurement;
DELETE FROM nsi_delivery_methods;

*/

INSERT INTO documents (id, type_id, author_id, status, access_limitation_id, contract_id, lot,
                       specification_id, customer_id, supplier_id, amount, date_signature,
                       registration_number, ost_id, create_date)
VALUES ('123e4567-e89b-12d3-a456-100000000000', 'MTR_SUPPLY_CONTRACT', '123e4567-e89b-12d3-a456-010000000000',
        'DRAFT', '123e4567-e89b-12d3-a456-810000000000', null, null, null,
        'customer_1', 'customer_2', '1000.01', '2020-01-31 10:00:00', 'registration_number_1', '123e4567-e89b-12d3-a456-000000001000', '2020-01-31 10:00:00'),
       ('123e4567-e89b-12d3-a456-200000000000', 'WAYBILL', '123e4567-e89b-12d3-a456-010000000000',
        'DRAFT', '123e4567-e89b-12d3-a456-820000000000', '123e4567-e89b-12d3-a456-100000000000', null,
        '123e4567-e89b-12d3-a456-300000000000', 'customer_3', 'customer_4', '5000.00', null, null, null, '2020-02-02 10:00:00'),
       ('123e4567-e89b-12d3-a456-300000000000', 'SPECIFICATION', '123e4567-e89b-12d3-a456-010000000000',
        'ACCEPTED', '123e4567-e89b-12d3-a456-830000000000', '123e4567-e89b-12d3-a456-100000000000', 'lot_1', null,
        'customer_5', 'customer_6', '3000.0002', null, null, null, '2020-01-25 10:00:00');
/*,
       ('123e4567-e89b-12d3-a456-400000000000', 'SPECIFICATION',
        '123e4567-e89b-12d3-a456-040000000000', 'ACCEPTED', '123e4567-e89b-12d3-a456-820000000000',
        null, 'lot_1', null, 'customer_7', 'customer_8', '2000',
        null, null, null, '2020-01-21 10:00:00'),
       ('123e4567-e89b-12d3-a456-500000000000', 'QUALITY_DOCUMENTS',
        '123e4567-e89b-12d3-a456-010000000000', 'APPROVAL_IN_PROGRESS', '123e4567-e89b-12d3-a456-820000000000',
        '123e4567-e89b-12d3-a456-100000000000',
        null, '123e4567-e89b-12d3-a456-400000000000', 'customer_3',
        'customer_4', '5000.00', null, null, null, '2020-02-02 10:00:00'),
       ('123e4567-e89b-12d3-a456-600000000000', 'MTR_INSURANCE_POLICY',
        '123e4567-e89b-12d3-a456-010000000000', 'DRAFT', 'CONFIDENTIALLY', '123e4567-e89b-12d3-a456-100000000000',
        null, '123e4567-e89b-12d3-a456-400000000000', 'customer_3',
        'customer_4', '5000.00', null, null, null, '2020-02-02 10:00:00'),
       ('123e4567-e89b-12d3-a456-700000000000', 'PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR',
        '123e4567-e89b-12d3-a456-010000000000', 'DRAFT', 'CONFIDENTIALLY', '123e4567-e89b-12d3-a456-100000000000',
        null, '123e4567-e89b-12d3-a456-400000000000', 'customer_3',
        'customer_4', '5000.00', null, null, null, '2020-02-02 10:00:00'); */

INSERT INTO nci_users (id, login, password, name, last_name, role, mail)
VALUES ('123e4567-e89b-12d3-a456-010000000000', 'user_1', '12345', 'user', 'A', 'user', 'user@gmail.com'),
       ('123e4567-e89b-12d3-a456-020000000000', 'user_2', '12345', 'user', 'B', 'user', 'user@gmail.com'),
       ('123e4567-e89b-12d3-a456-030000000000', 'user_3', '12345', 'user', 'C', 'user', 'user@gmail.com'),
       ('123e4567-e89b-12d3-a456-040000000000', 'admin', '12345', 'admin', 'D', 'admin', 'admin@gmail.com');

INSERT INTO documents_curators (document_id, curator_id)
VALUES ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-020000000000'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-030000000000'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-040000000000'),
       ('123e4567-e89b-12d3-a456-400000000000', '123e4567-e89b-12d3-a456-010000000000'),
       ('123e4567-e89b-12d3-a456-400000000000', '123e4567-e89b-12d3-a456-040000000000');

INSERT INTO type_links (id, type)
VALUES ('123e4567-e89b-12d3-a456-001000000000', 'SINGLE'),
       ('123e4567-e89b-12d3-a456-002000000000', 'OPTIONAL'),
       ('123e4567-e89b-12d3-a456-003000000000', 'MANDATORY'),
       ('123e4567-e89b-12d3-a456-004000000000', 'KIT');

INSERT INTO documents_link_documents (document_id, link_document_id, type_link_id,
                                          link_document_serial_number)
VALUES ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-100000000000',
        '123e4567-e89b-12d3-a456-001000000000', '1'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-300000000000',
        '123e4567-e89b-12d3-a456-001000000000', '3'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-400000000000',
        '123e4567-e89b-12d3-a456-002000000000', '4'),
       ('123e4567-e89b-12d3-a456-400000000000', '123e4567-e89b-12d3-a456-100000000000',
        '123e4567-e89b-12d3-a456-003000000000', '1');

INSERT INTO documents_history_bpm(id, serial_number, document_id, link_document_id, type_link)
VALUES ('123e4567-e89b-12d3-a456-000100000000', '1', '123e4567-e89b-12d3-a456-200000000000',
        '123e4567-e89b-12d3-a456-100000000000', 'MANDATORY'),
       ('123e4567-e89b-12d3-a456-000200000000', '2', '123e4567-e89b-12d3-a456-200000000000',
        '123e4567-e89b-12d3-a456-300000000000', 'MANDATORY'),
       ('123e4567-e89b-12d3-a456-000300000000', '3', '123e4567-e89b-12d3-a456-200000000000',
        '123e4567-e89b-12d3-a456-400000000000', 'MANDATORY');

INSERT INTO nci_objects_kis_up (kis_up, kis_up_id)
VALUES ('object_code_1', '1'),
       ('object_code_2', '2'),
       ('object_code_3', '3'),
       ('object_code_4', '4');

INSERT INTO nci_osts (id, display_value, internal_id)
VALUES ('123e4567-e89b-12d3-a456-000000001000', 'ost_1', '1'),
       ('123e4567-e89b-12d3-a456-000000002000', 'ost_2', '2'),
       ('123e4567-e89b-12d3-a456-000000003000', 'ost_3', '3'),
       ('123e4567-e89b-12d3-a456-000000004000', 'ost_4', '4');

INSERT INTO nci_attachments (id, serial_number, address)
VALUES ('123e4567-e89b-12d3-a456-000000000001', '1', 'address_1'),
       ('123e4567-e89b-12d3-a456-000000000002', '2', 'address_2'),
       ('123e4567-e89b-12d3-a456-000000000003', '3', 'address_3');

INSERT INTO documents_attachments (document_id, attachment_id)
VALUES ('123e4567-e89b-12d3-a456-100000000000', '123e4567-e89b-12d3-a456-000000000001'),
       ('123e4567-e89b-12d3-a456-100000000000', '123e4567-e89b-12d3-a456-000000000002'),
       ('123e4567-e89b-12d3-a456-300000000000', '123e4567-e89b-12d3-a456-000000000002'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-000000000002');


INSERT INTO nci_countries (id, country_name, internal_id)
VALUES ('123e4567-e89b-12d3-a456-000000001000', 'Абхазия', 1),
       ('123e4567-e89b-12d3-a456-000000002000', 'Австралия', 2),
       ('123e4567-e89b-12d3-a456-000000003000', 'Бангладеш', 3),
       ('123e4567-e89b-12d3-a456-000000004000', 'Беларусь', 4),
       ('123e4567-e89b-12d3-a456-000000005000', 'Вьетнам', 5);

INSERT INTO nci_types_of_transport (id, type_of_transport, internal_id)
VALUES ('123e4567-e89b-12d3-a456-000000010000', 'автомобильная грузоперевозка', 1),
       ('123e4567-e89b-12d3-a456-000000020000', 'железнодорожная грузоперевозка', 2),
       ('123e4567-e89b-12d3-a456-000000030000', 'морская грузоперевозка', 3),
       ('123e4567-e89b-12d3-a456-000000040000', 'авиаперевозка', 4);

INSERT INTO nci_units_measurement (id, unit, internal_id)
VALUES ('123e4567-e89b-12d3-a456-000000100000', 'кг', 1),
       ('123e4567-e89b-12d3-a456-000000200000', 'т', 2),
       ('123e4567-e89b-12d3-a456-000000300000', 'мм', 3),
       ('123e4567-e89b-12d3-a456-000000400000', 'см', 4),
       ('123e4567-e89b-12d3-a456-000000500000', 'м', 5),
       ('123e4567-e89b-12d3-a456-000000600000', 'км', 6),
       ('123e4567-e89b-12d3-a456-000000700000', 'шт', 7);


INSERT INTO nci_mtr_groups (id, mtr_group, internal_id)
VALUES ('123e4567-e89b-12d3-a456-210000000000', 'mtr_group_1', '1'),
       ('123e4567-e89b-12d3-a456-220000000000', 'mtr_group_2', '2'),
       ('123e4567-e89b-12d3-a456-230000000000', 'mtr_group_3', '3'),
       ('123e4567-e89b-12d3-a456-240000000000', 'mtr_group_4', '4'),
       ('123e4567-e89b-12d3-a456-250000000000', 'mtr_group_5', '5');

INSERT INTO nci_mtrs (id, name, internal_id, control_prod)
VALUES ('123e4567-e89b-12d3-a456-310000000000', 'гайка', 1, TRUE),
       ('123e4567-e89b-12d3-a456-320000000000', 'болт', 2, TRUE),
       ('123e4567-e89b-12d3-a456-330000000000', 'шайба', 3, TRUE),
       ('123e4567-e89b-12d3-a456-340000000000', 'шайба пружинная', 4, TRUE),
       ('123e4567-e89b-12d3-a456-350000000000', 'винт', 5, TRUE);

INSERT INTO nci_phases (id, name, name_rus)
VALUES ('123e4567-e89b-12d3-a456-410000000000', 'phase_1', 'этап_1'),
       ('123e4567-e89b-12d3-a456-420000000000', 'phase_2', 'этап_2'),
       ('123e4567-e89b-12d3-a456-430000000000', 'phase_3', 'этап_3'),
       ('123e4567-e89b-12d3-a456-440000000000', 'phase_4', 'этап_4'),
       ('123e4567-e89b-12d3-a456-450000000000', 'phase_5', 'этап_5');

INSERT INTO nci_class_contracts (id, class_contract, code)
VALUES ('123e4567-e89b-12d3-a456-510000000000', 'class_agr_1', 'code_1'),
       ('123e4567-e89b-12d3-a456-520000000000', 'class_agr_2', 'code_2'),
       ('123e4567-e89b-12d3-a456-530000000000', 'class_agr_3', 'code_3'),
       ('123e4567-e89b-12d3-a456-540000000000', 'class_agr_4', 'code_4'),
       ('123e4567-e89b-12d3-a456-550000000000', 'class_agr_5', 'code_5');

INSERT INTO nci_standard_forms (id, standard_form, internal_id)
VALUES ('123e4567-e89b-12d3-a456-610000000000', 'standard_form_1', '1'),
       ('123e4567-e89b-12d3-a456-620000000000', 'standard_form_2', '2'),
       ('123e4567-e89b-12d3-a456-630000000000', 'standard_form_3', '3'),
       ('123e4567-e89b-12d3-a456-640000000000', 'standard_form_4', '4'),
       ('123e4567-e89b-12d3-a456-650000000000', 'standard_form_5', '5');

INSERT INTO nci_termination_codes (id, termination_code, internal_id)
VALUES ('123e4567-e89b-12d3-a456-710000000000', 'cancellation_code_1', '1'),
       ('123e4567-e89b-12d3-a456-720000000000', 'cancellation_code_2', '2'),
       ('123e4567-e89b-12d3-a456-730000000000', 'cancellation_code_3', '3'),
       ('123e4567-e89b-12d3-a456-740000000000', 'cancellation_code_4', '4'),
       ('123e4567-e89b-12d3-a456-750000000000', 'cancellation_code_5', '5');

INSERT INTO nci_consignees (id, bukrs, external_consignee_id, first_consignee_name, second_consignee_name,
                            third_consignee_name, fourth_consignee_name, inn, kpp, okpo, first_railway_code,
                            first_freight_yard, first_station_code, first_customer_code, second_railway_code,
                            second_station_code, second_freight_yard, second_customer_code, third_railway_code,
                            third_station_code, third_customer_code, fourth_railway_code, fourth_station_code,
                            fourth_customer_code, fifth_railway_code, fifth_station_code, fifth_customer_code,
                            first_post_code, first_country, first_region, first_city, first_street, first_street_suppl,
                            first_house_number, first_house_number_suppl, second_post_code, second_country,
                            second_region, second_city, second_street, second_street_suppl, second_house_number,
                            second_house_number_suppl, sixth_railway_code, sixth_station_code, sixth_customer_code,
                            department, function, last_name, first_name, tel_number, tel_extension, fax_number,
                            fax_extension, fourth_country, fourth_region, fourth_district, fourth_city)
VALUES ('123e4567-e89b-12d3-a456-000000100000', 'БЕ', 'Внешний номер грузополучателя', 'имя 1', 'имя 2',
        'имя 3', 'имя 4', '6449013711', '644901001', '2308771683', '01',
        'Грузовой двор', '935741', '12345', '02',
        '155656', 'Грузовой двор', '123455', '03', '8563885',
        '954295', '04', '97422', '6548756',
        '05', '6548676', '664265', '111257', 'Россия',
        'МО', 'Москва', 'Садовая', 'примечание к улице', '21', 'дополнение к номеру дома',
        '000001', 'Россия', 'Приморский край', 'Владивосток', 'Амурска', 'дополнение к улице',
        '6', 'дополнение к номеру дома', '06', '6468465',
        '655465', 'Департамент', 'Должность', 'Иванов', 'Иван', '+78000000000', '243',
        '+78000000000', '001', 'RU', 'МО', 'район', 'Москва');

/*
INSERT INTO documents (id, type_id, d_type, serial_number,
                       contract_id, dop_contract_id,
                       author_id, nci_object_id, supplier_id,
                       customer_id, nci_ost_id, status, access,
                       lot, sum_no_vat, sum_vat, total_including_vat, contract_status,
                       nci_consignee_id, shipping_details, barcode, lkk_document_number, lus_document_number, comment)
VALUES ('123e4567-e89b-12d3-a456-555000000000', 'Спецификация',  'SPECIFICATION', 0001,
        '123e4567-e89b-12d3-a456-100000000000', '123e4567-e89b-12d3-a456-100000000000',
        '123e4567-e89b-12d3-a456-010000000000', '123e4567-e89b-12d3-a456-000001000000', 'Организация_2',
        '123e4567-e89b-12d3-a456-030000000000', '123e4567-e89b-12d3-a456-000000001000', 'DRAFT', 'WITHOUT_A_FINGERBOARD',
        '123456789', '1676265.55, 1255.4, 1675010.15', 'contract status',
        '123e4567-e89b-12d3-a456-000000100000', 'some shipping details', 'some barcode', '12345', '54321', 'some comment');
*/
INSERT INTO specification_table_entities (id, pid, position_number, delivery_method, position_code, nci_mtr_id,
                                          gost_ost_tu, code, nci_unit_of_measurement_id, quantity, price_no_vat,
                                          sum_no_vat, vat, sum_vat, amount_with_vat, contractor_id, nci_country_id,
                                          delivery_date, nci_type_of_transport_id, belonging_to_the_dsi,
                                          note, specification_id)
VALUES ('123e4567-e89b-12d3-a456-010000000000', 'some pid', 12345, FALSE, 54321, '123e4567-e89b-12d3-a456-310000000000',
        'some GOST', 'some code', '123e4567-e89b-12d3-a456-000000700000', 42, 50.5,
        2121, 20, 424.2, 2545.2, 'Contractor_1', '123e4567-e89b-12d3-a456-000000003000',
        '2022-10-20 10:00:00', '123e4567-e89b-12d3-a456-000000040000', 'some information about belonging to the dsi',
        'some note', '123e4567-e89b-12d3-a456-555000000000'),
       ('123e4567-e89b-12d3-a456-020000000000', 'some pid', 12345, TRUE, 54321, '123e4567-e89b-12d3-a456-320000000000',
        'some GOST', 'some code', '123e4567-e89b-12d3-a456-000000700000', 42, 50.5,
        2121, 20, 424.2, 2545.2, 'Contractor_1', '123e4567-e89b-12d3-a456-000000005000',
        '2022-10-20 10:00:00', '123e4567-e89b-12d3-a456-000000030000', 'some information about belonging to the dsi',
        'some note', '123e4567-e89b-12d3-a456-555000000000');

INSERT INTO nci_delivery_methods (id, delivery_method, internal_id)
VALUES ('123e4567-e89b-12d3-a456-910000000000', 'delivery_method_1', '1'),
       ('123e4567-e89b-12d3-a456-920000000000', 'delivery_method_2', '2'),
       ('123e4567-e89b-12d3-a456-930000000000', 'delivery_method_3', '3'),
       ('123e4567-e89b-12d3-a456-940000000000', 'delivery_method_4', '4'),
       ('123e4567-e89b-12d3-a456-950000000000', 'delivery_method_5', '5');

INSERT INTO nci_access_limitations (id, access_limitation, access_limitation_rus, code)
VALUES ('123e4567-e89b-12d3-a456-810000000000', 'WITHOUT_A_FINGERBOARD', 'Без грифа', 'code_1'),
       ('123e4567-e89b-12d3-a456-820000000000', 'CONFIDENTIALLY', 'Конфиденциально', 'code_2'),
       ('123e4567-e89b-12d3-a456-830000000000', 'SUPER_CONFIDENTIAL', 'супер конфиденциально', 'code_3');

INSERT INTO nci_contractors (id, contractor, contractor_rus)
VALUES ('100000000-e89b-12d3-a456-100000000000', 'customer_1', 'Организация_1'),
       ('200000000-e89b-12d3-a456-100000000000', 'customer_2', 'Организация_2'),
       ('300000000-e89b-12d3-a456-100000000000', 'customer_3', 'Организация_3'),
       ('400000000-e89b-12d3-a456-100000000000', 'customer_4', 'Организация_4'),
       ('500000000-e89b-12d3-a456-100000000000', 'customer_5', 'Организация_5'),
       ('600000000-e89b-12d3-a456-100000000000', 'customer_6', 'Организация_6'),
       ('700000000-e89b-12d3-a456-100000000000', 'customer_7', 'Организация_7'),
       ('800000000-e89b-12d3-a456-100000000000', 'customer_8', 'Организация_8');

INSERT INTO nci_document_statuses (id, internal_id, status_document, status_document_rus)
VALUES ('100000000-e89b-12d3-a456-100000000000', '1', 'ACTIVE', 'Активный'),
       ('200000000-e89b-12d3-a456-100000000000', '2', 'REMARKS_PREPARED', 'Сформированы замечания'),
       ('300000000-e89b-12d3-a456-100000000000', '3', 'TO_BE_SIGNED_BY_UKEP', 'На подписании УКЭП'),
       ('400000000-e89b-12d3-a456-100000000000', '4', 'ASSIGNMENT_OF_RESPONSIBLE', 'Назначение ответственных лиц'),
       ('500000000-e89b-12d3-a456-100000000000', '5', 'TO_BE_FAMILIARIZED', 'На ознакомлении'),
       ('600000000-e89b-12d3-a456-100000000000', '6', 'AWAITING_FOR_SIGNING', 'Ожидание подписания'),
       ('700000000-e89b-12d3-a456-100000000000', '7', 'PROJECT_CREATION', 'Создание проекта'),
       ('800000000-e89b-12d3-a456-100000000000', '8', 'NOT_ACTIVE', 'Неактивный'),
       ('900000000-e89b-12d3-a456-100000000000', '9', 'DRAFT', 'Черновик'),
       ('1000000000-e89b-12d3-a456-100000000000', '10', 'APPROVAL_IN_PROGRESS', 'На согласовании'),
       ('1100000000-e89b-12d3-a456-100000000000', '11', 'APPROVED', 'Согласован'),
       ('1200000000-e89b-12d3-a456-100000000000', '12', 'ACCEPTED', 'Принят'),
       ('1300000000-e89b-12d3-a456-100000000000', '13', 'APPROVAL_IN_PROGRESS_IN_SED', 'На согласовании в СЭД'),
       ('1400000000-e89b-12d3-a456-100000000000', '14', 'FORMED', 'Сформирован'),
       ('1500000000-e89b-12d3-a456-100000000000', '15', 'WORK_IN_PROGRESS', 'В работе'),
       ('1600000000-e89b-12d3-a456-100000000000', '16', 'ARCHIVED', 'В архиве');


INSERT INTO nci_ost_agents (id, ost_agent, internal_id)
VALUES ('100000000-e89b-12d3-a456-100000000000', 'ost_agent_1', '1'),
       ('200000000-e89b-12d3-a456-100000000000', 'ost_agent_2', '2'),
       ('300000000-e89b-12d3-a456-100000000000', 'ost_agent_3', '3'),
       ('400000000-e89b-12d3-a456-100000000000', 'ost_agent_4', '4');



-- select * from documents;
-- select * from nci_users;


INSERT INTO nci_document_types (id, name, name_rus)
values ('123e4567-e89b-12d3-a456-111000000000', 'DOCUMENT', 'Документ'),
       ('123e4567-e89b-12d3-a456-112000000000', 'CONTRACT', 'Договор'),
       ('123e4567-e89b-12d3-a456-113000000000', 'MTR_SUPPLY_CONTRACT',
        'Договор на поставку МТР (включая дополнительные соглашения)'),
       ('123e4567-e89b-12d3-a456-114000000000', 'SPECIFICATION_TO_THE_MTR_CONTRACT', 'Спецификация к договору МТР'),
       ('123e4567-e89b-12d3-a456-115000000000', 'INFORMATION_ON_THE_SUPPLIERS_CHAIN_OF_OWNERSHIP',
        'Сведения о цепочке собственников Поставщика'),
       ('123e4567-e89b-12d3-a456-116000000000', 'MTP_INSURANCE_POLICY', 'Полис страхования МТР'),
       ('123e4567-e89b-12d3-a456-117000000000', 'MTR_PRODUCTION_AND_SHIPMENT_PLAN', 'План изготовления и отгрузки МТР'),
       ('123e4567-e89b-12d3-a456-118000000000',
        'INFORMATION_ON_THE_PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR,',
        'Информация о ходе изготовления и подготовке к отгрузке МТР'),
       ('123e4567-e89b-12d3-a456-119000000000', 'DESIGN_DOCUMENTS_FOR_THE_SUPPLIER',
        'Конструкторская документация (для поставщика)'),
       ('123e4567-e89b-12d3-a456-120000000000', 'KMD_WORKING_DOCUMENTATION', 'Рабочая документация по КМД'),
       ('123e4567-e89b-12d3-a456-121000000000', 'ACT_OR_DOCUMENT_ON_KMD_APPROVAL', 'Акт/документ о согласовании КМД'),
       ('123e4567-e89b-12d3-a456-122000000000',
        'ACTS_OF_DETECTED_DEFECTS_OR_PRODUCT_FAILURES_WITHIN_THE_SCOPE_OF_THE_WARRANTY_CASE',
        'Акты о выявленных дефектах либо отказах продукции в рамках гарантийного случая'),
       ('123e4567-e89b-12d3-a456-123000000000', 'ACTS_OF_DEFECTS_OR_PRODUCT_FAILURES_WITHIN_THE_WARRANTY_PERIOD',
        'Акты о выявленных дефектах либо отказах продукции в рамках гарантийного случая(более 2 лет)'),
       ('123e4567-e89b-12d3-a456-124000000000', 'ACT_OF_INSPECTION_OF_PRODUCT_MANUFACTURING',
        'Акт проверки изготовления продукции'),
       ('123e4567-e89b-12d3-a456-125000000000', 'PROTOCOL_FOR_ACCEPTANCE_TESTS_OF_PRODUCTS',
        'Протокол приемо-сдаточных испытаний продукции'),
       ('123e4567-e89b-12d3-a456-126000000000', 'INFORMING_ABOUT_HAZARDOUS_SUBSTANCES',
        'Информирование об опасных веществах'),
       ('123e4567-e89b-12d3-a456-127000000000', 'NOTIFICATION_OF_CHANGE_OF_SHIPPING_DETAILS',
        'Уведомление о смене отгрузочных реквизитов'),
       ('123e4567-e89b-12d3-a456-128000000000',
        'AGREEMENT_ON_THE_SUPPLY_OF_PRODUCTS_IN_ACCOUNT_OF_WHICH_ADVANCE_PAYMENT',
        'Соглашение о поставке продукции, в счет которой уплачен аванс (пр. 6 к договору на поставку МТР)'),
       ('123e4567-e89b-12d3-a456-129000000000', 'RECONCILIATION_ACT_ON_THE_PART_OF_THE_SUPPLIER',
        'Акт сверки со стороны Поставщика'),
       ('123e4567-e89b-12d3-a456-130000000000', 'RECONCILIATION_ACT_ON_THE_PART_OF_OST', 'Акт сверки со стороны ОСТ'),
       ('123e4567-e89b-12d3-a456-131000000000', 'CARGO_CUSTOMS_DECLARATION', 'Грузовая таможенная декларация'),
       ('123e4567-e89b-12d3-a456-132000000000', 'WAYBILL', 'Транспортная накладная'),
       ('123e4567-e89b-12d3-a456-133000000000', 'SPECIFICATION', 'Спецификация'),
       ('123e4567-e89b-12d3-a456-134000000000', 'QUALITY_DOCUMENTS', 'Документы о качестве(проставление отметки ТН)');

/*
    RECEIPT_OF_CARGO_RECEPTION, //Квитанция о приеме груза
    AIR_WAYBILL, //Авиа-накладная
            RECEIPT_OF_POSTAL_ITEMS, //Квитанция о приеме почтовых отправлений
    INFORMATION_ABOUT_THE_COST_OF_ZIP_AND_INDIVIDUAL_UNITS_MTR, //Сведения о стоимости ЗИП и отдельных единиц МТР
    PASSPORT_OR_CERTIFICATE_OF_QUALITY_FOR_THE_SUPPLIER_FACTORY, //Паспорт или сертификат качества для завода поставщика
    ACT_ON_THE_RESULTS_OF_THE_INSPECTION_OF_PRODUCTS, //Акт о результатах проверки изделий
    ACT_ON_RECEIPT_OF_INVENTORY_OS_14_M_4, //Акт о приеме МТР (ОС-14, М-4)
    ACT_ON_THE_TRANSFER_OF_INVENTORY_OS_15_M_15,  //Акт о передаче МТР (ОС-15, М-15)
    ACT_OF_DETECTION_OF_DEFECTS_IN_MATERIALS_OR_EQUIPMENT_OS_16_M_7,  //Акт выявления дефектов материалов/оборудования (ОС-16, М-7)
    NOTIFICATION_OF_ACCEPTANCE_FOR_SAFE_CUSTODY,  //Уведомление о приемке на ответственное хранение
    ACT_OF_ACCEPTANCE_OF_SERVICES_RENDERED_FOR_RESPONSIBLE_STORAGE_OF_PRODUCTS_NOT_ACCEPTED_BY_THE_BUYER,  //Акт сдачи-приемки оказанных услуг по ответственному хранению не принятой Покупателем Продукции
    NOTIFICATION_SPECIFYING_THE_PLACE_AND_TIME_OF_THE_PNR_OR_SH_MR,  //Уведомление с указанием места и времени проведения ПНР/ШМР
    ACT_ON_THE_PNR_OR_SH_MR,  //Акт по ПНР/ШМР
    DEFECTIVE_ACT,  //Дефектный акт (письмо-уведомление и акт в свободной форме)
    PRICE_LIST_CONTRACT_WITH_APPENDICES_INCLUDING_APPLICATIONS_AND_SPECIFICATIONS,  //Прейскурантный договор с приложениями, включая заявки и спецификации
    APPLICATION_TO_THE_CONTRACT_AT_UNIT_RATES,  //Заявка (к договору по единичным расценкам)
            SPECIFICATION_TO_THE_CONTRACT_FOR_UNIT_RATES,  //Спецификация (к договору по единичным расценкам)

            ACT_ON_THE_RECOGNITION_OF_PRODUCTS_AS_DEFECTIVE,  //Акт о признании продукции дефектной (о признании продукции не соответствующей количеству и/или комплектности и/или марке)
    TN_SURVEILLANCE_CONTRACT,  //Договор ТН-Надзор
    OST_APPLICATION,  //Заявка ОСТ (план поставок, спецификация на поднадзорную продукцию, указанная в плане поставок)
    REPORT_ON_SUPERVISED_PRODUCTS_THAT_HAVE_UNDERGONE_TECHNICAL_SUPERVISION_AT_MANUFACTURING_ENTERPRISES,  //Отчет по поднадзорной продукции, прошедшей технический надзор на предприятиях-изготовителях
    TECHNICAL_ACT,  //Технический акт
    ACT_OF_ACCEPTANCE_OF_SERVICES_RENDERED,  //Акт сдачи-приёмки оказанных услуг
    CONTRACT_WITH_A_LOGISTICS_OPERATOR,  //Договор с логистическим оператором (перевозка и экспедирование груза)
    POWER_OF_ATTORNEY,  //Доверенность
            TNL_ORDER_PLUS_COST_APPROVAL_PROTOCOL,  //Поручение ТНЛ+Протокол согласования стоимости
    ACT_OF_SERVICES_RENDERED,  //Акт оказанных услуг
    PAYMENT_INVOICE,  //Счёт на оплату
    RAILWAY_WAYBILL,  //Транспортная железнодорожная накладная (копия) ГУ-27
    SPECIAL_PERMISSION_FOR_CTG,  //Спецразрешение на КТГ
    APPLICATION_FOR_CARGO_TRANSPORTATION_GU_12_FORM,  //Заявка на перевозку грузов (форма ГУ-12)
    INSTRUCTION_TO_ACCEPT_MTR_FOR_STORAGE,  //Поручение на прием МТР на хранение
    PROTOCOL_OF_AGREEMENT_OF_UNIT_RATES_FOR_THE_LIST_OF_SERVICES,  //Протокол согласования единичных расценок по перечню услуг
    ACT_OF_ACCEPTANCE_AND_DELIVERY_OF_SERVICES_RENDERED_BY_TNL,  //Акт приемки- сдачи оказанных услуг ТНЛ
    CONTRACT_FOR_THE_IMPLEMENTATION_OF_THE_CMR,  //Договор на выполнение СМР (включая дополнительные соглашения)
    MONTHLY_AND_DAILY_SCHEDULE_OF_WORK,  //Месячно-суточный график выполнения работ
    PROGRESS_REPORT_ON_THE_DELIVERY_OF_MATERIALS_AND_EQUIPMENT_BY_THE_CONTRACTOR,  //Отчет о ходе поставки материалов и оборудования Подрядчиком
    CONTRACTOR_S_CHAIN_OF_OWNERSHIP,  //Цепочка собственников Подрядчика
    PLAN_FOR_INVOLVING_CO_EXECUTORS_FROM_AMONG_SMALL_AND_MEDIUM_SIZED_BUSINESSES_IN_THE_EXECUTION_OF_THE_CONTRACT,  //План привлечения Соисполнителей из числа субъектов малого и среднего предпринимательства к исполнению контракта
    INFORMATION_ABOUT_THE_CONCLUSION_OF_THE_CONTRACT_WITH_THE_CO_EXECUTOR_FROM_AMONG_SMALL_AND_MEDIUM_SIZED_BUSINESSES,  //Сведения о заключении договора с Соисполнителем из числа субъектов малого и среднего предпринимательства
    INFORMATION_ON_CO_EXECUTORS,  //Информация по Соисполнителям
    BANK_GUARANTEE,  //Банковская гарантия
    CMR_INSURANCE_POLICY,  //Полис страхования СМР
    STATEMENT_OF_PROCESSING_OF_CUSTOMER_S_GOODS_MADE_ON_DELIVERY,  //Ведомость переработки давальческих материалов поставки заказчика
    REGISTER_OF_MOUNTED_EQUIPMENT_OF_THE_CUSTOMER_S_DELIVERY,  //Реестр смонтированного оборудования поставки Заказчика
    REGISTER_OF_MOUNTED_EQUIPMENT_OF_THE_CONTRACTOR_S_DELIVERY,  //Реестр смонтированного оборудования поставки Подрядчика
    STATEMENT_OF_THE_EQUIPMENT_INSTALLATION_OF_WHICH_IS_STARTED,  //Ведомость оборудования, монтаж которого начат
    STATEMENT_OF_ACCOUNTING_OF_EQUIPMENT_SUPPLIED_BY_THE_CONTRACTOR,  //Ведомость учета поставленных Подрядчиком оборудования,
        ACT_OF_RECEIPT_OF_TANGIBLE_ASSETS_RECEIVED_DURING_DISASSEMBLY_AND_DISMANTLING_OF_FIXED_ASSETS_M_35,  //Акт об оприходовании материальных ценностей, полученных при разборке и демонтаже основных средств (М-35)
    INVOICE,  //Счёт-фактура (в т.ч. корректировочный)
            INVOICE_FOR_PAYMENT,  //Счет на оплату
    TORG_12_BILL_OF_LADING,  //ТОРГ 12, товарная накладная
    KS_2_AN_ACT_OF_ACCEPTANCE_OF_WORK_PERFORMED,  //КС-2, акт о приемке выполненных работ (в т.ч. корректировочный)
    KC_3_CERTIFICATE_OF_THE_COST_OF_WORK_PERFORMED_AND_COSTS,  //КС-3, справка о стоимости выполненных работ и затрат (в т.ч. корректировочный)
    KS_6_A_LOG_OF_COMPLETED_WORK,  //КС-6а, журнал учета выполненных работ
    ACT_OF_ACCEPTANCE_TRANSFER_OF_MATERIAL_ASSETS_FOR_STORAGE,  //Акт о приеме-передаче товарно-материальных ценностей на хранение (Типовая форма № МХ-1)
    ACT_ON_THE_RETURN_OF_INVENTORY_ITEMS_DEPOSITED,  //Акт о возврате товарно-материальных ценностей, сданных на хранение (Типовая форма № МХ-3)
    AGREEMENT_ON_THE_DEFINITION_OF_WORK_FOR_WHICH_AN_ADVANCE_PAYMENT_WAS_MADE,  //Соглашение об определении работ (поставок материалов),в счет которых уплачен аванс (часть аванса)
    BILL_OF_SALE_OF_MATERIALS_TO_EXTERNAL_PARTIES,  //Накладная на отпуск материалов на сторону (Типовая форма М-15)
    FORM_KC_11_ACT_OF_ACCEPTANCE_OF_COMPLETED_CONSTRUCTION,  //Форма КС-11, акт приемки законченного строительством Объекта
    FORM_KS_14_ACT_OF_ACCEPTANCE_OF_THE_COMPLETED_CONSTRUCTION_OF_THE_FACILITY_BY_THE_ACCEPTANCE_COMMISSION,  //Форма КС-14, акт приемки законченного строительством Объекта приемочной комиссией
    FORM_F_36_THE_ACT_OF_ACCEPTANCE_INTO_OPERATION_OF_THE_COMPLETED_OVERHAUL_OF_THE_OBJECT,  //Форма Ф-36, акт приемки в эксплуатацию законченного капитальным ремонтом объекта
    FORM_OS_3_ACT_OF_ACCEPTANCE_OF_REPAIRED_RECONSTRUCTED_MODERNIZED_FIXED_ASSETS,  //Форма ОС-3, Акт о приеме-сдаче отремонтированных, реконструированных, модернизированных объектов основных средств
    LIST_OF_EQUIPMENT_TRANSFERRED_TO_PROVIDE_WORK_TO_THE_CONTRACTOR,  //Перечень оборудования, передаваемого для обеспечения работ подрядной организации
    TRANSFER_AND_ACCEPTANCE_ACT_OF_THE_EQUIPMENT_TRANSFERRED_TO_SUPPORT_THE_WORK,  //Акт приема-передачи Оборудования, передаваемого для обеспечения работ
    ACT_OF_SET_OFF_OF_MUTUAL_CLAIMS_ON_THE_PART_OF_THE_COUNTERPARTY,  //Акт зачета взаимных требований со стороны Контрагента
    ACT_OF_SET_OFF_OF_RECIPROCAL_CLAIMS_ON_THE_PART_OF_OST,  //Акт зачета взаимных требований со стороны ОСТ
    WORKING_DOCUMENTATION_FOR_KMD,  //Рабочая документация по КМД
    DESIGN_DOCUMENTATION_FOR_THE_CONTRACTOR,  //Конструкторская документация для контрагента
    REMARKS_ON_THE_WORKING_DOCUMENTATION_OF_KMD,  //Замечания к рабочей документации по КМД
    ACT_OR_DOCUMENT_ON_THE_APPROVAL_OF_KMD,  //Акт/документ о согласовании КМД
    PROJECT_OF_WORK_EXECUTION,  //Проект производства работ (ППР)
    CLAIM_OR_DEMAND_FOR_LIQUIDATED_DAMAGES_INITIATOR_OST,  //Претензия / требование об уплате неустойки (инициатор - ОСТ)
    CLAIM_OR_DEMAND_FOR_LIQUIDATED_DAMAGES_INITIATOR_CONTRACTOR,  //Претензия / требование об уплате неустойки (инициатор - Контрагент)
    PAYMENT_DOCUMENT_ON_PAYMENT_OF_PENALTIES,  //Платежный документ об оплате неустойки
    CLAIMS_SETTLEMENT_DOCUMENT,  //Документ об урегулировании претензионных требовании
    GENERAL_DOCUMENT_INFORMATION_ON_FORENSIC_ACTIVITIES,  //Общий документ -сведения о судебно-исковой деятельности (постановления, решения, определения суда и т.д.)
    ACT_ON_THE_NEED_TO_PERFORM_CERTIFICATION_OF_ADDITIONAL_OR_EXCLUDED_WORKS_KOR_01,  //Акт о необходимости выполнения (освидетельствования) дополнительных/исключаемых работ (КОР-01)
    STATEMENT_OF_DEFECTS_OR_DEFECTS_DETECTED_WITHIN_THE_WARRANTY_PERIOD,  //Акт о выявленных дефектах/недостатках в Гарантийный срок
    STATEMENT_OF_RECTIFICATION_OF_DEFECTS_OR_DEFECTS_WITHIN_THE_WARRANTY_PERIOD,  //Акт об устранении дефектов/недостатков в Гарантийный срок
    SCHEDULE_FOR_MOBILIZATION_OF_HUMAN_AND_TECHNICAL_RESOURCES_AT_THE_SITE_IN_ORDER_TO_ELIMINATE_DEFECTS_OR_DEFICIENCIES,  //График мобилизации людских и технических ресурсов на объект в целях устранения дефектов/недостатков
    SCHEDULE_OF_ELIMINATION_OF_DETECTED_DEFECTS_OR_DEFECTS_DURING_THE_WARRANTY_PERIOD,  //График устранения выявленных дефектов/недостатков в гарантийный срок
    WARNING_OR_RECOMMENDATION_OF_DETECTED_VIOLATIONS_OF_THE_SK,  //Предупреждение/предписание о выявленных нарушениях СК
    NOTIFICATION_OF_RECTIFICATION_OF_IDENTIFIED_VIOLATIONS_OF_THE_SK,  //Уведомление об устранении выявленных нарушений СК
    PASSPORT_OR_CERTIFICATE_OF_QUALITY_FOR_THE_COUNTERPARTY, //Паспорт или сертификат качества
    ACT_OF_INCOMING_INSPECTION_OF_MATERIALS_AND_EQUIPMENT,  //Акт входного контроля материалов и оборудования
    NOTICE_OF_OFFSET_OF_COLLATERAL_AMOUNT,  //Уведомление о зачете суммы обеспечения
    NOTIFICATION_OF_OFFSET_OF_AMOUNT_OF_ADVANCE_PAYMENT_ISSUED,  //Уведомление о зачете суммы выданного аванса
    NOTICE_OF_SECURITY_RETAINER_IN_LIEU_OF_CONTRACT_SECURITY,  //Уведомление о гарантийном удержании взамен обеспечения по контракту
    STATEMENT_OF_THE_COST_OF_WORK_AND_SERVICES_PERFORMED_MATERIALS_AND_EQUIPMENT_DELIVERED //Справка о стоимости выполненных работ и услуг, поставленных материалов и оборудования); */