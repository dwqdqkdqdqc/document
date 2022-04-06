INSERT INTO documents (id, type_doc, create_date, create_user, status, access, contract, lot,
                       specification)
VALUES ('123e4567-e89b-12d3-a456-100000000000', 'WAYBILL', '2020-01-20 10:00:00',
        '123e4567-e89b-12d3-a456-010000000000', 'IN_WORK', 'COMMON', '123e4567-e89b-12d3-a456-000010000000', null,
        '123e4567-e89b-12d3-a456-300000000000'),
       ('123e4567-e89b-12d3-a456-200000000000', 'WAYBILL', '2020-01-21 10:00:00',
        '123e4567-e89b-12d3-a456-010000000000', 'IN_WORK', 'COMMON', '123e4567-e89b-12d3-a456-000030000000', null,
        '123e4567-e89b-12d3-a456-400000000000'),
       ('123e4567-e89b-12d3-a456-300000000000', 'SPECIFICATION', '2020-01-22 10:00:00',
        '123e4567-e89b-12d3-a456-010000000000', 'IN_WORK', 'COMMON', '123e4567-e89b-12d3-a456-000020000000', null,
        null),
       ('123e4567-e89b-12d3-a456-400000000000', 'SPECIFICATION', '2020-02-23 10:00:00',
        '123e4567-e89b-12d3-a456-040000000000', 'REQUIRES_CLARIFICATION', 'FOR_INTERNAL_USE',
        '123e4567-e89b-12d3-a456-000020000000', null, null);


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

INSERT INTO doc_history_bpm (id, author, event, date, statuses, bpmn_statuses, comments, id_doc)
VALUES ('123e4567-e89b-12d3-a456-000100000000', '123e4567-e89b-12d3-a456-020000000000', 'event_1',
        '2020-01-20 10:00:00', 'status_1', 'bpmn_status_1', 'comment_1', '123e4567-e89b-12d3-a456-400000000000'),
       ('123e4567-e89b-12d3-a456-000200000000', '123e4567-e89b-12d3-a456-020000000000', 'event_2',
        '2020-01-21 10:00:00', 'status_2', 'bpmn_status_2', 'comment_1', '123e4567-e89b-12d3-a456-400000000000'),
       ('123e4567-e89b-12d3-a456-000300000000', '123e4567-e89b-12d3-a456-010000000000', 'event_3',
        '2020-01-22 10:00:00', 'status_3', 'bpmn_status_3', 'comment_1', '123e4567-e89b-12d3-a456-200000000000'),
       ('123e4567-e89b-12d3-a456-000400000000', '123e4567-e89b-12d3-a456-040000000000', 'event_4',
        '2020-01-23 10:00:00', 'status_4', 'bpmn_status_4', 'comment_1', '123e4567-e89b-12d3-a456-200000000000');

INSERT INTO contracts (id, responsible_user_id, organization_id, status)
VALUES ('123e4567-e89b-12d3-a456-000010000000', '123e4567-e89b-12d3-a456-020000000000', 'organization_1', 'status_1'),
       ('123e4567-e89b-12d3-a456-000020000000', '123e4567-e89b-12d3-a456-020000000000', 'organization_2', 'status_2'),
       ('123e4567-e89b-12d3-a456-000030000000', '123e4567-e89b-12d3-a456-010000000000', 'organization_3', 'status_3'),
       ('123e4567-e89b-12d3-a456-000040000000', '123e4567-e89b-12d3-a456-040000000000', 'organization_4', 'status_4');

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

INSERT INTO pids (pid, document_id)
VALUES ('123e4567-e89b-12d3-a456-000000100000', '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000200000', '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000300000', '123e4567-e89b-12d3-a456-200000000000'),
       ('123e4567-e89b-12d3-a456-000000400000', '123e4567-e89b-12d3-a456-100000000000');

INSERT INTO factory_numbers (factory_number, document_id)
VALUES ('123e4567-e89b-12d3-a456-000000010000', '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000020000', '123e4567-e89b-12d3-a456-100000000000'),
       ('123e4567-e89b-12d3-a456-000000030000', '123e4567-e89b-12d3-a456-200000000000'),
       ('123e4567-e89b-12d3-a456-000000040000', '123e4567-e89b-12d3-a456-100000000000');

select *
from documents;
select *
from users;

