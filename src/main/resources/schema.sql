/*
DELETE
FROM documents;
DELETE
FROM users;
DELETE
FROM documents_curators;
DELETE
FROM type_relation;
DELETE
FROM documents_relating_documents;
DELETE
FROM document_history_bpm;
DELETE
FROM construction_objects;
DELETE
FROM documents_construction_objects;
DELETE
FROM nci_osts;
DELETE
FROM nci_pids;
DELETE
FROM nci_factory_numbers;
DELETE
FROM attachments;
DELETE
FROM documents_attachments;
*/

INSERT INTO documents (id, type, date_of_creation, author_id, status, access, contract_id, lot,
                       specification_id, customer, supplier, amount, date_of_signing, document_registration_number, nci_ost_id)
VALUES ('123e4567-e89b-12d3-a456-100000000000', 'CONTRACT', '2020-01-20 10:00:00',
        '123e4567-e89b-12d3-a456-010000000000', 'IN_WORK', 'COMMON', null, null,
        '123e4567-e89b-12d3-a456-300000000000', 'ORGANIZATION_1', 'FACTORY_1', '1000.01', '2020-01-21 10:00:00', 'registration_number_1',
        '123e4567-e89b-12d3-a456-000000001000'),
       ('123e4567-e89b-12d3-a456-200000000000', 'WAYBILL', '2020-01-21 10:00:00',
        '123e4567-e89b-12d3-a456-010000000000', 'IN_WORK', 'COMMON', '123e4567-e89b-12d3-a456-100000000000', null,
        '123e4567-e89b-12d3-a456-400000000000', 'ORGANIZATION_2', 'FACTORY_2', '5000.00', null, null, null),
       ('123e4567-e89b-12d3-a456-300000000000', 'SPECIFICATION', '2020-01-22 10:00:00',
        '123e4567-e89b-12d3-a456-010000000000', 'IN_WORK', 'COMMON', '123e4567-e89b-12d3-a456-100000000000', 'lot_1',
        '123e4567-e89b-12d3-a456-400000000000', 'ORGANIZATION_3', 'FACTORY_3', '3000.0002', null, null, null),
       ('123e4567-e89b-12d3-a456-400000000000', 'SPECIFICATION', '2020-02-23 10:00:00',
        '123e4567-e89b-12d3-a456-040000000000', 'REQUIRES_CLARIFICATION', 'FOR_INTERNAL_USE',
        null, 'lot_1', null, 'ORGANIZATION_1', 'FACTORY_1', '2000', null, null, null);


INSERT INTO users (id, login, password, name, last_name, role, mail)
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

INSERT INTO type_relation (id, type)
VALUES ('123e4567-e89b-12d3-a456-001000000000', 'SINGLE'),
       ('123e4567-e89b-12d3-a456-002000000000', 'OPTIONAL'),
       ('123e4567-e89b-12d3-a456-003000000000', 'MANDATORY'),
       ('123e4567-e89b-12d3-a456-004000000000', 'KIT');

INSERT INTO documents_relating_documents (document_id, relating_document_id, type_relation_id,
                                          relating_document_serial_number)
VALUES ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-100000000000',
        '123e4567-e89b-12d3-a456-001000000000', '1'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-300000000000',
        '123e4567-e89b-12d3-a456-001000000000', '3'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-400000000000',
        '123e4567-e89b-12d3-a456-002000000000', '4'),
       ('123e4567-e89b-12d3-a456-400000000000', '123e4567-e89b-12d3-a456-100000000000',
        '123e4567-e89b-12d3-a456-003000000000', '1');

INSERT INTO document_history_bpm(id, serial_number, document_id, relating_document_id, type_relation)
VALUES ('123e4567-e89b-12d3-a456-000100000000', '1', '123e4567-e89b-12d3-a456-200000000000',
        '123e4567-e89b-12d3-a456-100000000000', 'MANDATORY'),
       ('123e4567-e89b-12d3-a456-000200000000', '2', '123e4567-e89b-12d3-a456-200000000000',
        '123e4567-e89b-12d3-a456-300000000000', 'MANDATORY'),
       ('123e4567-e89b-12d3-a456-000300000000', '3', '123e4567-e89b-12d3-a456-200000000000',
        '123e4567-e89b-12d3-a456-400000000000', 'MANDATORY');

INSERT INTO construction_objects (id, name, address, organization)
VALUES ('123e4567-e89b-12d3-a456-000001000000', 'construction_object_1', 'address_1', 'organization_1'),
       ('123e4567-e89b-12d3-a456-000002000000', 'construction_object_2', 'address_2', 'organization_2'),
       ('123e4567-e89b-12d3-a456-000003000000', 'construction_object_3', 'address_3', 'organization_3'),
       ('123e4567-e89b-12d3-a456-000004000000', 'construction_object_4', 'address_4', 'organization_4');

INSERT INTO documents_construction_objects (document_id, construction_object_id)
VALUES ('123e4567-e89b-12d3-a456-100000000000', '123e4567-e89b-12d3-a456-000002000000'),
       ('123e4567-e89b-12d3-a456-100000000000', '123e4567-e89b-12d3-a456-000004000000'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-000004000000'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-000001000000');

INSERT INTO nci_osts (id, display_value, internal_id)
VALUES ('123e4567-e89b-12d3-a456-000000001000', 'ost_1', '1'),
       ('123e4567-e89b-12d3-a456-000000002000', 'ost_2', '2'),
       ('123e4567-e89b-12d3-a456-000000003000', 'ost_3', '3'),
       ('123e4567-e89b-12d3-a456-000000004000', 'ost_4', '4');

INSERT INTO nci_pids (id, pid, document_id)
VALUES ('123e4567-e89b-12d3-a456-000000000100', '123e4567-e89b-12d3-a456-000000100000',
        '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000000200', '123e4567-e89b-12d3-a456-000000200000',
        '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000000300', '123e4567-e89b-12d3-a456-000000300000',
        '123e4567-e89b-12d3-a456-200000000000'),
       ('123e4567-e89b-12d3-a456-000000000400', '123e4567-e89b-12d3-a456-000000400000',
        '123e4567-e89b-12d3-a456-100000000000');

INSERT INTO nci_factory_numbers (id, factory_number, document_id)
VALUES ('123e4567-e89b-12d3-a456-000000000010', '123e4567-e89b-12d3-a456-000000010000',
        '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000000020', '123e4567-e89b-12d3-a456-000000020000',
        '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000000030', '123e4567-e89b-12d3-a456-000000030000',
        '123e4567-e89b-12d3-a456-200000000000'),
       ('123e4567-e89b-12d3-a456-000000000040', '123e4567-e89b-12d3-a456-000000040000',
        '123e4567-e89b-12d3-a456-100000000000');

INSERT INTO attachments (id, serial_number, address)
VALUES ('123e4567-e89b-12d3-a456-000000000001', '1', 'address_1'),
       ('123e4567-e89b-12d3-a456-000000000002', '2', 'address_2'),
       ('123e4567-e89b-12d3-a456-000000000003', '3', 'address_3');

INSERT INTO documents_attachments (document_id, attachment_id)
VALUES ('123e4567-e89b-12d3-a456-100000000000', '123e4567-e89b-12d3-a456-000000000001'),
       ('123e4567-e89b-12d3-a456-100000000000', '123e4567-e89b-12d3-a456-000000000002'),
       ('123e4567-e89b-12d3-a456-300000000000', '123e4567-e89b-12d3-a456-000000000002'),
       ('123e4567-e89b-12d3-a456-200000000000', '123e4567-e89b-12d3-a456-000000000002');

-- select * from documents;
-- select * from users;

