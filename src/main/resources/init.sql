DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS documents;
DROP TABLE IF EXISTS documents_curators;
DROP INDEX IF EXISTS document_id_curators_id_idx;
--DROP INDEX IF EXISTS doc_id_data_id_name_data_type_idx;
DROP TABLE IF EXISTS type_relation;
DROP TABLE IF EXISTS documents_relating_documents;
DROP TABLE IF EXISTS document_history_bpm;
DROP TABLE IF EXISTS contracts;
DROP TABLE IF EXISTS construction_objects;
DROP TABLE IF EXISTS documents_construction_objects;
DROP TABLE IF EXISTS nci_osts;
DROP TABLE IF EXISTS nci_pids;
DROP TABLE IF EXISTS nci_factory_numbers;
DROP TABLE IF EXISTS attachments;
DROP TABLE IF EXISTS documents_attachments;
DROP TABLE IF EXISTS nci_contractors;
DROP TABLE IF EXISTS nci_document_types;
DROP TABLE IF EXISTS nci_units_of_measurement;
DROP TABLE IF EXISTS nci_countries;
DROP TABLE IF EXISTS nci_types_of_transport;
DROP TABLE IF EXISTS nci_mtrs;
DROP TABLE IF EXISTS nci_consignees;
DROP TABLE IF EXISTS specifications_tables_entities;


CREATE TABLE documents
(                                                          --Base fields
    id                           VARCHAR PRIMARY KEY NOT NULL,
    type_id                      VARCHAR             NULL,
    d_type                       VARCHAR             NULL,
    serial_number                BIGINT              NULL,
    date_of_creation             TIMESTAMP DEFAULT now(),
    author_id                    VARCHAR             NULL,
    content                      bytea               NULL,
    status                       VARCHAR             NULL,
    access                       VARCHAR             NULL,
    comment
                                 VARCHAR             NULL,
    contract_id                  VARCHAR             NULL,
    specification_id             VARCHAR             NULL,
    construction_object_id       VARCHAR             NULL,
    factory_number               VARCHAR             NULL,
    barcode                      VARCHAR             NULL,
    lkk_document_number          VARCHAR             NULL,
    lus_document_number          VARCHAR             NULL,
    customer_id                  VARCHAR             NULL,
    supplier_id                  VARCHAR             NULL,
    amount                       numeric   default 0 NULL,

--Fields of other classes
    lot                          VARCHAR             NULL, --Specification
    total_sum_no_vat             NUMERIC DEFAULT 0   NULL, --Specification
    total_vat                    NUMERIC DEFAULT 0   NULL, --Specification
    total_sum_vat                NUMERIC DEFAULT 0   NULL, --Specification
    contract_status              VARCHAR             NULL, --Specification
    dop_contract_id              VARCHAR             NULL, --Specification
    supervised_products          BOOLEAN DEFAULT FALSE,    --Specification
    nci_consignee_id             VARCHAR             NULL, --Specification
    shipping_details             VARCHAR             NULL, --Specification
    date_of_signing              TIMESTAMP           NULL, --contract
    document_registration_number VARCHAR             NULL, --contract
    nci_ost_id                   VARCHAR             NULL, --contract, Specification
    contract_subject             VARCHAR             NULL, --contract
    reg_number                   VARCHAR             NULL, --contract
    inn                          VARCHAR             NULL, --contract
    contractor_id                VARCHAR             NULL, --contract
    contract_class               VARCHAR             NULL, --contract
    typical_form                 VARCHAR             NULL, --contract
    contract_view                VARCHAR             NULL, --contract
    frame_contract               VARCHAR             NULL  --contract

    --  FOREIGN KEY (create_user) REFERENCES users (id)
);
create unique index documents_serial_number_uindex on documents (serial_number);


CREATE TABLE users
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

CREATE TABLE type_relation
(
    id   VARCHAR NULL,
    type VARCHAR NULL
);

CREATE TABLE documents_relating_documents
(
    document_id                     VARCHAR NULL,
    relating_document_id            VARCHAR NULL,
    type_relation_id                VARCHAR NULL,
    relating_document_serial_number VARCHAR NULL
);
--CREATE UNIQUE INDEX doc_id_data_id_name_data_type_idx ON documents_linked_documents (document_id, linked_document_id/*, type_relation_id*/);

CREATE TABLE document_history_bpm
(
    id                   varchar null,
    serial_number        bigint  null,
    document_id          varchar null,
    relating_document_id varchar null,
    type_relation        varchar null
);

CREATE TABLE construction_objects
(
    id           VARCHAR NULL,
    name         VARCHAR NULL,
    address      VARCHAR NULL,
    organization VARCHAR NULL
);

CREATE TABLE documents_construction_objects
(
    document_id            VARCHAR NULL,
    construction_object_id VARCHAR NULL
);
--CREATE UNIQUE INDEX document_id_construction_object_id_idx ON documents_construction_objects (document_id, construction_object_id);


CREATE TABLE nci_osts
(
    id            VARCHAR      NULL,
    display_value VARCHAR(150) NULL, --Наименование ОСТ
    internal_id   integer      NULL  --Внутренний (технический) номер записи SAP MDM
);

CREATE TABLE nci_pids
(
    id          VARCHAR NULL,
    pid         VARCHAR NULL,
    document_id VARCHAR NULL
);

CREATE TABLE nci_factory_numbers
(
    id             VARCHAR NULL,
    factory_number VARCHAR NULL,
    document_id    VARCHAR NULL
);

CREATE TABLE attachments
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

CREATE TABLE nci_contractors
(
    name     VARCHAR NULL,
    name_rus VARCHAR NULL
);

CREATE TABLE nci_document_types
(
    id       VARCHAR NOT NULL,
    name     VARCHAR NULL,
    name_rus VARCHAR NULL
);

CREATE TABLE nci_units_of_measurement
(
    id            VARCHAR      NULL,
    display_value VARCHAR(150) NULL, --Наименование ЕИ по ОКЕИ
    internal_id   integer      NULL --Внутренний номер записи SAP MDM
);

CREATE TABLE nci_countries
(
    id            VARCHAR      NULL,
    display_value VARCHAR(150) NULL,
    internal_id   integer      NULL
);

CREATE TABLE nci_types_of_transport
(
    id            VARCHAR      NULL,
    display_value VARCHAR(150) NULL,
    internal_id   integer      NULL
);

CREATE TABLE nci_mtrs
(
    id                      VARCHAR      NULL,
    display_value           VARCHAR      NULL,
    name                    VARCHAR      NULL,
    type                    VARCHAR      NULL,
    internal_id             integer      NULL,
    value                   VARCHAR      NULL
);

CREATE TABLE nci_consignees
(
    id            VARCHAR      NULL,
    display_value VARCHAR(150) NULL,
    internal_id   integer      NULL
);

CREATE TABLE specifications_tables_entities
(
    id                          VARCHAR PRIMARY KEY     NOT NULL,
    pid                         VARCHAR                 NULL,
    position_number             BIGINT                  NULL,
    delivery_method             BOOLEAN DEFAULT FALSE,
    position_code               BIGINT                  NULL,
    nci_mtr_id                  VARCHAR                 NULL,
    gost_ost_tu                 VARCHAR                 NULL,
    code                        VARCHAR                 NULL,
    nci_unit_of_measurement_id  VARCHAR                 NULL,
    quantity                    BIGINT                  NULL,
    price_no_vat                NUMERIC DEFAULT 0       NULL,
    sum_no_vat                  NUMERIC DEFAULT 0       NULL,
    vat                         NUMERIC DEFAULT 0       NULL,
    sum_vat                     NUMERIC DEFAULT 0       NULL,
    amount_with_vat             NUMERIC DEFAULT 0       NULL,
    contractor_id               VARCHAR                 NULL,
    nci_country_id              VARCHAR                 NULL,
    delivery_date               TIMESTAMP               NULL,
    nci_type_of_transport_id    VARCHAR                 NULL,
    belonging_to_the_dsi        VARCHAR                 NULL,
    specification_id            VARCHAR                 NULL,
    note                        VARCHAR                 NULL
)

