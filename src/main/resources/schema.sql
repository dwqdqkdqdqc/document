DROP TABLE IF EXISTS nci_users;
DROP TABLE IF EXISTS documents;
DROP TABLE IF EXISTS documents_curators;
DROP INDEX IF EXISTS document_id_curators_id_idx;
--DROP INDEX IF EXISTS doc_id_data_id_name_data_type_idx;
DROP TABLE IF EXISTS type_links;
DROP TABLE IF EXISTS documents_link_documents;
DROP TABLE IF EXISTS documents_history_bpm;
DROP TABLE IF EXISTS contracts;
DROP TABLE IF EXISTS nci_objects_kis_up;
DROP TABLE IF EXISTS documents_objects;
DROP TABLE IF EXISTS nci_osts;
DROP TABLE IF EXISTS nci_attachments;
DROP TABLE IF EXISTS documents_attachments;
DROP TABLE IF EXISTS nci_contractors;
DROP TABLE IF EXISTS nci_document_types;
DROP TABLE IF EXISTS nci_mtr_groups;
DROP TABLE IF EXISTS nci_mtrs;
DROP TABLE IF EXISTS nci_phases;
DROP TABLE IF EXISTS nci_class_contracts;
DROP TABLE IF EXISTS nci_standard_forms;
DROP TABLE IF EXISTS nci_termination_codes;
DROP TABLE IF EXISTS nci_countries;
DROP TABLE IF EXISTS nci_types_of_transport;
DROP TABLE IF EXISTS nci_consignees;
DROP TABLE IF EXISTS specification_table_entities;
DROP TABLE IF EXISTS nci_units_measurement;
DROP TABLE IF EXISTS nsi_delivery_methods;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS comment_attachments;
DROP TABLE IF EXISTS nci_access_limitations;
DROP TABLE IF EXISTS nci_document_statuses;
DROP TABLE IF EXISTS nci_ost_agents;


CREATE TABLE documents
(                                                                      --Base fields
    id                                       VARCHAR PRIMARY KEY NOT NULL,
    type_id                                  VARCHAR             NULL,
    -- d_type                                VARCHAR             NULL,
    serial_number                            bigserial           not null,
    create_date                              TIMESTAMP DEFAULT now(),
    create_date_short                        TIMESTAMP DEFAULT now(),
    registration_number                      VARCHAR             NULL, --contract
    date_signature                           TIMESTAMP           NULL, --contract
    additional_agreement_date                TIMESTAMP           NULL, --contract
    object_kis_up_id                         VARCHAR             NULL,
    author_id                                VARCHAR             NULL,
    ost_id                                   VARCHAR             NULL, --contract
    access_limitation_id                     VARCHAR             NULL,
    status_id                                VARCHAR             NULL,
    comment                                  VARCHAR             NULL,
    ost_agent_id                             VARCHAR             NULL,
    class_contract_id                        VARCHAR             NULL, --contract
    standard_form_id                         VARCHAR             NULL, --contract
    starting_date                            TIMESTAMP           null, --MtrInsurancePolicy
    end_date                                 TIMESTAMP           null, --MtrInsurancePolicy
    starting_date_work                       TIMESTAMP           null,
    end_date_work                            TIMESTAMP           null,
    date_of_termination                      TIMESTAMP           NULL, --contract
    sum_no_vat                               numeric   default 0 NULL, --MtrInsurancePolicy
    sum_vat                                  numeric   default 0 NULL, --MtrInsurancePolicy
    total_sum_vat                            numeric   default 0 NULL, --MtrInsurancePolicy
    status_zakupki                           VARCHAR             NULL, --contract
    role_id                                  VARCHAR             NULL, --contract
    responsible_id                           VARCHAR             NULL, --contract
    factory_number                           VARCHAR             NULL,
    pid_number                               INTEGER             NULL,
    barcode                                  VARCHAR             NULL,
    lkk_number                               VARCHAR             NULL,
    lkk_date                                 TIMESTAMP           NULL,
    lus_number                               VARCHAR             NULL,
    dop_Contract_id                          VARCHAR             NULL,
    lot_number                               VARCHAR             NULL,
    status_contract                          VARCHAR             NULL,
    position_number                          BIGINT              NULL,
    delivery_method                          BOOLEAN   DEFAULT FALSE,
    position_code                            BIGINT              NULL,
    mtr_id                                   VARCHAR             NULL,
    gost_ost_tu                              VARCHAR             NULL,
    code                                     VARCHAR             NULL,
    unit_of_measurement_id                   VARCHAR             NULL,
    quantity                                 BIGINT              NULL,
    price_no_vat                             NUMERIC   DEFAULT 0 NULL,
    vat                                      NUMERIC   DEFAULT 0 NULL,
    amount_with_vat                          NUMERIC   DEFAULT 0 NULL,
    contractor_id                            VARCHAR             NULL,
    country_id                               VARCHAR             NULL,
    delivery_date                            TIMESTAMP           NULL,
    type_of_transport_id                     VARCHAR             NULL,
    belonging_to_the_dsi                     VARCHAR             NULL,
    specification_id                         VARCHAR             NULL,
    note                                     VARCHAR             NULL,
    customer_inn                             VARCHAR             NULL,
    customer_ogrn                            VARCHAR             NULL,
    customer_company_name                    VARCHAR             NULL,
    customer_okved                           VARCHAR             NULL,
    customer_manager_fio                     VARCHAR             NULL,
    customer_passport_number                 VARCHAR             NULL,
    contract_number_and_date                 VARCHAR             NULL,
    other_essential_conditions               VARCHAR             NULL,
    number_in_order                          BIGINT              null,
    owner_fio                                VARCHAR             NULL,
    owner_inn                                VARCHAR             NULL,
    owner_ogrn_ogrni                         VARCHAR             NULL,
    owner_registration_address               VARCHAR             NULL,
    document_of_individual                   VARCHAR             NULL,
    supporting_document                      VARCHAR             NULL,
    legal_entity                             VARCHAR             NULL,
    owner_manager_fio                        VARCHAR             NULL,
    information_on_composit_executive_bodies VARCHAR             NULL,
    policy_period_beginning                  TIMESTAMP           null,
    policy_period_end                        TIMESTAMP           null,
    number_policy                            VARCHAR             NULL,
    date_policy                              TIMESTAMP           null,
    inn_insurance_company                    VARCHAR             NULL,
    name_insurance_company                   VARCHAR             NULL,


    content                                  bytea               NULL,
    contract_id                              VARCHAR             NULL,
    customer_id                              VARCHAR             NULL,
    supplier_id                              VARCHAR             NULL,
    amount                                   numeric   default 0 NULL,
    deleted                                  BOOLEAN   default false,

--Fields of other classes
    lot                                      VARCHAR             NULL, --Specification

    additional_agreement_number              VARCHAR             NULL, --contract
    additional_agreement_specification_id    VARCHAR             NULL, --contract     ???


    contract_subject                         VARCHAR             NULL, --contract
    reg_number                               VARCHAR             NULL, --contract
    inn                                      VARCHAR             NULL, --contract
    contract_class                           VARCHAR             NULL, --contract
    typical_form                             VARCHAR             NULL, --contract
    contract_view                            VARCHAR             NULL, --contract
    frame_contract                           VARCHAR             NULL, --contract
    framework_agreement                      boolean             null, --contract
    subject_of_the_contract                  VARCHAR             NULL, --contract


    organization_id                          VARCHAR             NULL, --contract

    termination_code_id                      VARCHAR             NULL, --contract

    nci_mtr_group_id                         VARCHAR             NULL, --ProgressOfProductionForShipmentOfMtr
    phase_number                             VARCHAR             NULL, --ProgressOfProductionForShipmentOfMtr
    nci_phase_id                             VARCHAR             NULL, --ProgressOfProductionForShipmentOfMtr
    plan_date                                TIMESTAMP           null, --ProgressOfProductionForShipmentOfMtr
    fact_date                                TIMESTAMP           null, --ProgressOfProductionForShipmentOfMtr
    verify_document                          VARCHAR             NULL, --ProgressOfProductionForShipmentOfMtr

    contract_status                          VARCHAR             NULL,
    nci_consignee_id                         VARCHAR             NULL,
    shipping_details                         integer             null, --какой тип? уточнить
    nci_units_measurement_id                 VARCHAR             NULL,
    delivery_method_id                       VARCHAR             NULL
);
create unique index documents_serial_number_uindex on documents (serial_number);


CREATE TABLE nci_users
(
    id                    VARCHAR PRIMARY KEY NOT NULL,
    login                 VARCHAR             NULL,
    password              VARCHAR             NULL,
    name                  VARCHAR             NULL,
    middle_name           VARCHAR             NULL,
    last_name             VARCHAR             NULL,
    organization          VARCHAR             NULL,
    role                  VARCHAR             NULL,
    personal_phone_number VARCHAR             NULL,
    work_phone_number     VARCHAR             NULL,
    mail                  VARCHAR             NULL,
    photo                 bytea               NULL,
    ip_address            VARCHAR             NULL,
    event                 VARCHAR             NULL,
    work_permission       boolean DEFAULT 'true',
    user_tasks            VARCHAR             NULL
);

CREATE TABLE documents_curators
(
    document_id VARCHAR NULL,
    curator_id  VARCHAR NULL
);
CREATE UNIQUE INDEX document_id_curators_id_idx ON documents_curators (document_id, curator_id);

CREATE TABLE type_links
(
    id   VARCHAR NULL,
    type VARCHAR NULL
);

CREATE TABLE documents_link_documents
(
    document_id                 VARCHAR NULL,
    link_document_id            VARCHAR NULL,
    type_link_id                VARCHAR NULL,
    link_document_serial_number VARCHAR NULL
);
--CREATE UNIQUE INDEX doc_id_data_id_name_data_type_idx ON documents_linked_documents (document_id, linked_document_id/*, type_link_id*/);

CREATE TABLE documents_history_bpm
(
    id               varchar null,
    serial_number    bigint  null,
    document_id      varchar null,
    link_document_id varchar null,
    type_link        varchar null
);

CREATE TABLE nci_objects_kis_up
(
    id        VARCHAR NULL,
    kis_up    VARCHAR NULL, --Код объекта КИС УП
    kis_up_id integer NULL  --Уникальный идентификатор ID КИС УП
);

CREATE TABLE documents_objects
(
    document_id   VARCHAR NULL,
    object_kis_up VARCHAR NULL
);
--CREATE UNIQUE INDEX document_id_construction_object_id_idx ON documents_construction_objects (document_id, construction_object_id);


CREATE TABLE nci_osts
(
    id            VARCHAR      NULL,
    display_value VARCHAR(150) NULL, --Наименование ОСТ
    internal_id   integer      NULL  --Внутренний (технический) номер записи SAP MDM
);

CREATE TABLE nci_attachments
(
    id            VARCHAR NULL,
    serial_number bigint  null,
    address       varchar null --Путь к вложению, размещенном на контент - сервере
);

CREATE TABLE documents_attachments
(
    document_id   VARCHAR NULL,
    attachment_id VARCHAR NULL
);

CREATE TABLE nci_document_types
(
    id       VARCHAR NOT NULL,
    name     VARCHAR NULL,
    name_rus VARCHAR NULL
);

CREATE TABLE nci_mtr_groups
(
    id          VARCHAR NOT NULL,
    mtr_group   VARCHAR NOT NULL, --Код Группы МТР+ Наименование Группы МТР
    internal_id Integer NULL
);

CREATE TABLE nci_phases
(
    id       VARCHAR NOT NULL,
    name     VARCHAR NOT NULL,
    name_rus VARCHAR NULL
);

CREATE TABLE nci_class_contracts
(
    id             VARCHAR NULL,
    class_contract VARCHAR NULL, --Наименование Объекта строительства
    code           VARCHAR NULL  --Внутренний (технический) номер записи SAP MDM
);

CREATE TABLE nci_standard_forms
(
    id            VARCHAR NULL,
    standard_form VARCHAR NULL, --Наименование - Типовая форма
    internal_id   integer NULL  --Внутренний (технический) номер записи SAP MDM
);

CREATE TABLE nci_termination_codes
(
    id               VARCHAR NULL,
    termination_code VARCHAR NULL, --Наименование – Код расторжения
    internal_id      VARCHAR NULL  --Внутренний (технический) номер записи SAP MDM
);



CREATE TABLE nci_units_measurement
(
    id          VARCHAR NOT NULL,
    unit        VARCHAR NULL, --Наименование ЕИ по ОКЕИ
    internal_id integer NULL  --Внутренний номер записи SAP MDM
);

CREATE TABLE nci_countries
(
    id           VARCHAR      NOT NULL,
    country_name VARCHAR(150) NULL,
    internal_id  integer      NULL
);

CREATE TABLE nci_types_of_transport
(
    id                VARCHAR      NOT NULL,
    type_of_transport VARCHAR(150) NULL,
    internal_id       integer      NULL
);

CREATE TABLE nci_mtrs
(
    id           VARCHAR NULL,
    name         VARCHAR NULL,
    internal_id  integer NULL,
    control_prod BOOLEAN DEFAULT FALSE
);

CREATE TABLE nci_consignees
(
    id                        VARCHAR NOT NULL,
    bukrs                     VARCHAR NULL,
    external_consignee_id     VARCHAR NULL,
    first_consignee_name      VARCHAR NULL,
    second_consignee_name     VARCHAR NULL,
    third_consignee_name      VARCHAR NULL,
    fourth_consignee_name     VARCHAR NULL,
    inn                       VARCHAR NULL,
    kpp                       VARCHAR NULL,
    okpo                      VARCHAR NULL,
    first_railway_code        VARCHAR NULL,
    first_freight_yard        VARCHAR NULL,
    first_station_code        VARCHAR NULL,
    first_customer_code       VARCHAR NULL,
    second_railway_code       VARCHAR NULL,
    second_station_code       VARCHAR NULL,
    second_freight_yard       VARCHAR NULL,
    second_customer_code      VARCHAR NULL,
    third_railway_code        VARCHAR NULL,
    third_station_code        VARCHAR NULL,
    third_customer_code       VARCHAR NULL,
    fourth_railway_code       VARCHAR NULL,
    fourth_station_code       VARCHAR NULL,
    fourth_customer_code      VARCHAR NULL,
    fifth_railway_code        VARCHAR NULL,
    fifth_station_code        VARCHAR NULL,
    fifth_customer_code       VARCHAR NULL,
    first_post_code           VARCHAR NULL,
    first_country             VARCHAR NULL,
    first_region              VARCHAR NULL,
    first_city                VARCHAR NULL,
    first_street              VARCHAR NULL,
    first_street_suppl        VARCHAR NULL,
    first_house_number        VARCHAR NULL,
    first_house_number_suppl  VARCHAR NULL,
    second_post_code          VARCHAR NULL,
    second_country            VARCHAR NULL,
    second_region             VARCHAR NULL,
    second_city               VARCHAR NULL,
    second_street             VARCHAR NULL,
    second_street_suppl       VARCHAR NULL,
    second_house_number       VARCHAR NULL,
    second_house_number_suppl VARCHAR NULL,
    sixth_railway_code        VARCHAR NULL,
    sixth_station_code        VARCHAR NULL,
    sixth_customer_code       VARCHAR NULL,
    department                VARCHAR NULL,
    function                  VARCHAR NULL,
    last_name                 VARCHAR NULL,
    first_name                VARCHAR NULL,
    tel_number                VARCHAR NULL,
    tel_extension             VARCHAR NULL,
    fax_number                VARCHAR NULL,
    fax_extension             VARCHAR NULL,
    fourth_country            VARCHAR NULL,
    fourth_region             VARCHAR NULL,
    fourth_district           VARCHAR NULL,
    fourth_city               VARCHAR NULL
);

CREATE TABLE specification_table_entities
(
    id                         VARCHAR PRIMARY KEY NOT NULL,
    pid                        VARCHAR             NULL,
    position_number            BIGINT              NULL,
    delivery_method            BOOLEAN DEFAULT FALSE,
    position_code              BIGINT              NULL,
    nci_mtr_id                 VARCHAR             NULL,
    gost_ost_tu                VARCHAR             NULL,
    code                       VARCHAR             NULL,
    nci_unit_of_measurement_id VARCHAR             NULL,
    quantity                   BIGINT              NULL,
    price_no_vat               NUMERIC DEFAULT 0   NULL,
    sum_no_vat                 NUMERIC DEFAULT 0   NULL,
    vat                        NUMERIC DEFAULT 0   NULL,
    sum_vat                    NUMERIC DEFAULT 0   NULL,
    amount_with_vat            NUMERIC DEFAULT 0   NULL,
    contractor_id              VARCHAR             NULL,
    nci_country_id             VARCHAR             NULL,
    delivery_date              TIMESTAMP           NULL,
    nci_type_of_transport_id   VARCHAR             NULL,
    belonging_to_the_dsi       VARCHAR             NULL,
    specification_id           VARCHAR             NULL,
    note                       VARCHAR             NULL
);

CREATE TABLE nsi_delivery_methods
(
    id              VARCHAR PRIMARY KEY NOT NULL,
    delivery_method VARCHAR             NULL, --Наименование – Способ доставки
    internal_id     integer             null  --Внутренний (технический) номер записи SAP MDM
);

CREATE TABLE nci_access_limitations
(
    id                    VARCHAR PRIMARY KEY NOT NULL,
    access_limitation     VARCHAR             NULL, --Наименование – Грифы доступа
    access_limitation_rus VARCHAR             NULL, --Наименование – Грифы доступа
    code                  VARCHAR             NULL  --Внутренний (технический) номер записи SAP MDM
);

CREATE TABLE nci_contractors
(
    id              VARCHAR PRIMARY KEY NOT NULL,
    contractor      VARCHAR             NULL, --Наименование Контрагента
    contractor_rus  VARCHAR             NULL, --Наименование Контрагента
    internal_id     integer             null, --Внутренний номер записи SAP MDM
    internal_guid   integer             null, --GUID делового партнера
    inn             VARCHAR             NULL,
    kpp             VARCHAR             NULL,
    okpo            VARCHAR             NULL,
    okdp            VARCHAR             NULL,
    ogrn            VARCHAR             NULL,
    okved           VARCHAR             NULL,
    okato           VARCHAR             NULL,
    oktmo           VARCHAR             NULL,
    phone           VARCHAR             NULL,
    fax             VARCHAR             NULL,
    email           VARCHAR             NULL,
    address         VARCHAR             NULL,
    contractor_type integer             null, --Значение из справочника «Тип контрагента»
    bp_type_lt_id   integer             null  --Тип Контрагента
);

CREATE TABLE nci_document_statuses
(
    id                  VARCHAR PRIMARY KEY NOT NULL,
    internal_id         INTEGER             null, --Id Статусы документов
    status_document     VARCHAR             NULL, --Наименование – Статусы документов
    status_document_rus VARCHAR             NULL
);

CREATE TABLE nci_ost_agents
(
    id          VARCHAR      NULL,
    ost_agent   VARCHAR(150) NULL, --Наименование ОСТ агента
    internal_id integer      NULL  --Внутренний (технический) номер записи SAP MDM
);


/*
CREATE TABLE test_table_a
(
    name   VARCHAR NULL,
    name_b VARCHAR NULL
);

CREATE TABLE test_table_b
(
    name VARCHAR NULL
);

INSERT INTO test_table_a (name, name_b)
VALUES ('a_1', 'b_1'),
       ('a_2', 'b_2'),
       ('a_3', 'b_3'),
       ('a_4', 'b_5');

INSERT INTO test_table_b (name)
VALUES ('b_1'),
       ('b_2'),
       ('b_3'),
       ('b_4');

DROP TABLE IF EXISTS test_table_a;
DROP TABLE IF EXISTS test_table_b;
*/
