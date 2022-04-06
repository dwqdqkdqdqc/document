DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS documents;
DROP TABLE IF EXISTS documents_curators;
DROP INDEX IF EXISTS document_id_curators_id_idx;
--DROP INDEX IF EXISTS doc_id_data_id_name_data_type_idx;
DROP TABLE IF EXISTS type_relation;
DROP TABLE IF EXISTS documents_relating_documents;
DROP TABLE IF EXISTS doc_history_bpm;
DROP TABLE IF EXISTS contracts;
DROP TABLE IF EXISTS construction_objects;
DROP TABLE IF EXISTS documents_construction_objects;
DROP TABLE IF EXISTS pids;
DROP TABLE IF EXISTS factory_numbers;

CREATE TABLE documents
(                                                --Base fields
    id                  VARCHAR PRIMARY KEY NOT NULL,
    type_doc            VARCHAR             NOT NULL,
    serial_number       BIGSERIAL           NOT NULL,
    create_date         TIMESTAMP           NOT NULL,
    create_user         VARCHAR             NOT NULL,
    content             bytea               NULL,
    status              VARCHAR             NOT NULL,
    access              VARCHAR             NULL,
    comment             VARCHAR             NULL,
    contract            VARCHAR             NOT NULL,
    specification       VARCHAR             NULL,
    construction_object VARCHAR             NULL,
    -- pid                 VARCHAR             NULL,
    factory_number      VARCHAR             NULL,
    barcode             VARCHAR             NULL,
    lkk_document_number VARCHAR             NULL,
    lus_document_number VARCHAR             NULL,

--Fields of other classes
    lot                 VARCHAR             NULL --Specification

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
    document_id VARCHAR NOT NULL,
    curator_id  VARCHAR NOT NULL
);
CREATE UNIQUE INDEX document_id_curators_id_idx ON documents_curators (document_id, curator_id);

CREATE TABLE type_relation
(
    id   VARCHAR NOT NULL,
    type VARCHAR NOT NULL
);

CREATE TABLE documents_relating_documents
(
    document_id                     VARCHAR NOT NULL,
    relating_document_id            VARCHAR NOT NULL,
    type_relation_id                VARCHAR NOT NULL,
    relating_document_serial_number VARCHAR NULL
);
--CREATE UNIQUE INDEX doc_id_data_id_name_data_type_idx ON documents_linked_documents (document_id, linked_document_id/*, type_relation_id*/);

CREATE TABLE doc_history_bpm
(
    id            varchar   not null,
    author        varchar   not null,
    event         varchar   null,
    date          timestamp null,
    statuses      varchar   null,
    bpmn_statuses varchar   null,
    comments      varchar   null,
    id_doc        varchar   not null
);

CREATE TABLE contracts
(
    id                  varchar not null,
    responsible_user_id varchar not null,
    organization_id     varchar not null,
    status              varchar not null
);

CREATE TABLE construction_objects
(
    id           VARCHAR NOT NULL,
    name         VARCHAR NOT NULL,
    address      VARCHAR NULL,
    organization VARCHAR NULL
);

CREATE TABLE documents_construction_objects
(
    document_id            VARCHAR NOT NULL,
    construction_object_id VARCHAR NOT NULL
);
--CREATE UNIQUE INDEX document_id_construction_object_id_idx ON documents_construction_objects (document_id, construction_object_id);

CREATE TABLE pids
(
    pid         VARCHAR NOT NULL,
    document_id VARCHAR NOT NULL
);

CREATE TABLE factory_numbers
(
    factory_number VARCHAR NOT NULL,
    document_id    VARCHAR NOT NULL
);



