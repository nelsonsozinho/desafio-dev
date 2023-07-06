--Roles
INSERT INTO cnab_role (id, role_name, description)
VALUES ('81ec177a-8ca5-4f6b-8111-4d23f3ee38f0', 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');
INSERT INTO cnab_role (id, role_name, description)
VALUES ('52f47d9f-3946-4e30-aabd-d5cb607e28c3', 'STANDARD_USER', 'Standard User - Has no admin rights');

--Users
--password superuser
INSERT INTO cnab_user (id, first_name, last_name, password, username)
VALUES ('789eae4c-1bcf-48c5-beb0-fb5a80c36927', 'Admin', 'Admin', '$2a$10$xLqlSqjcSXRPGi19NkJL1uTyPqVZ5HbfAk47xZc1tFBXhP11QF1XO', 'superuser');
-- password test1234
INSERT INTO cnab_user (id, first_name, last_name, password, username)
VALUES ('9f3a280c-3903-4fe6-b503-c41de9a3e9c9', 'John', 'Doe', '$2a$10$5AWyzymSnNypg9BkMOyKE.zA05GtRKHCoWimh.q2w.KAO5koBYPM6', 'john.doe');

--User Roles
INSERT INTO users_roles(user_id, role_id) VALUES ('789eae4c-1bcf-48c5-beb0-fb5a80c36927', '81ec177a-8ca5-4f6b-8111-4d23f3ee38f0');
INSERT INTO users_roles(user_id, role_id) VALUES ('789eae4c-1bcf-48c5-beb0-fb5a80c36927', '52f47d9f-3946-4e30-aabd-d5cb607e28c3');
INSERT INTO users_roles(user_id, role_id) VALUES ('9f3a280c-3903-4fe6-b503-c41de9a3e9c9', '52f47d9f-3946-4e30-aabd-d5cb607e28c3');

insert into cnab_transaction (id, data, "value", cpf, card_number, last_modified_at, id_type, store_name, owner_name)
values ('65beba1e-8798-4d2b-9ebc-aec689ed0743', '2019-03-01', 0000014200, '09620676017', '4753****3153', now(), 'd5a2bf6f-2476-47e0-b312-2183f2e824b5', 'JOÃO MACEDO', 'BAR DO JOÃO');
insert into cnab_transaction (id, data, "value", cpf, card_number, last_modified_at, id_type, owner_name, store_name)
values ('fcf56781-568f-4a23-aafd-202b4f186807', '2019-03-01', 0000013200, '55641815063', '3123****7687', now(), 'bb66b200-67c2-4707-b0f4-f40fc3ba9d1e', 'MARIA JOSEFINA', 'LOJA DO Ó - MATRIZ');
insert into cnab_transaction (id, data, "value", cpf, card_number, last_modified_at, id_type,  owner_name, store_name)
values ('8d119494-318e-4817-8052-62decd4222d5', '2019-03-01', 0000012200, '84515254073', '6777****1313', now(), 'd5a2bf6f-2476-47e0-b312-2183f2e824b5', 'MARCOS PEREIRA', 'MERCADO DA AVENIDA');
insert into cnab_transaction (id, data, "value", cpf, card_number, last_modified_at, id_type, owner_name, store_name)
values ('ee0b6c65-ffeb-482f-ab7a-319d8c39dcc4', '2019-03-01', 0000010700, '84515254073', '8723****9987', now(), '262aa586-d257-49f0-a87d-0e100a050fdb', 'MARCOS PEREIRA', 'MERCADO DA AVENIDA');
insert into cnab_transaction (id, data, "value", cpf, card_number, last_modified_at, id_type,  owner_name, store_name)
values ('cf6e907d-e73b-4884-8a84-f8de889f0b82', '2019-03-01', 0000050200, '84515254073', '8473****1231', now(), '262aa586-d257-49f0-a87d-0e100a050fdb', 'MARCOS PEREIRA', 'MERCADO DA AVENIDA');
insert into cnab_transaction (id, data, "value", cpf, card_number, last_modified_at, id_type,  owner_name, store_name)
values ('fe36bf33-f0f4-4558-b3bc-30c44ae6b826', '2019-03-01', 0000080200, '84515254073', '8473****1231', now(), 'bb66b200-67c2-4707-b0f4-f40fc3ba9d1e', 'MARCOS PEREIRA', 'MERCADO DA AVENIDA');
